package dao.impl;

import common.Constants;
import common.SQLQueries;
import config.Configuration;
import dao.CustomersDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import model.Customer;
import model.Order;
import model.errors.RestaurantError;
import model.xml.OrdersXML;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import services.OrderService;

import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class CustomerImpl implements CustomersDAO {
    private final OrderService orderService;
    private DBConnectionPool db;

    @Inject
    public CustomerImpl(DBConnectionPool db, OrderService orderService) {
        this.db = db;
        this.orderService = orderService;
    }

    @Override
    public Either<RestaurantError, List<Customer>> getAll() {
        Either<RestaurantError, List<Customer>> result = null;
        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
        List<Customer> customers = jtm.query(SQLQueries.SELECT_FROM_CUSTOMERS, new CustomerMapper());
        if (customers.isEmpty()) {
            result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_CUSTOMERS));
        } else {
            result = Either.right(customers);
        }
        return result;
    }

    @Override
    public Either<RestaurantError, Customer> get(int id) {
        Either<RestaurantError, Customer> result = null;
        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
        List<Customer> customers = jtm.query(SQLQueries.SELECT_FROM_CUSTOMERS_WHERE_ID, new CustomerMapper(), id);
        if (customers.isEmpty()) {
            result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_CUSTOMERS));
        } else {
            result = Either.right(customers.get(0));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, Integer> save(Customer c) {
        Either<RestaurantError, Integer> result = null;
        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
        try {
            String insertCredentialsSQL = SQLQueries.INSERT_INTO_CREDENTIALS_USERNAME_PASSWORD_VALUES;
            String insertCustomerSQL = SQLQueries.INSERT_INTO_CUSTOMERS_ID_FIRST_NAME_LAST_NAME_EMAIL_PHONE_DATE_OF_BIRTH_VALUES;

            String username = c.getCredential().getNombre();
            String password = c.getCredential().getPassword();
            String firstName = c.getFirstname();
            String lastName = c.getLastname();
            String email = c.getEmail();
            String phone = c.getPhone();
            LocalDate dob = c.getDob();

            KeyHolder keyHolder = new GeneratedKeyHolder();
            PreparedStatementCreator preparedStatementCreator = con -> {
                PreparedStatement ps = con.prepareStatement(insertCredentialsSQL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, username);
                ps.setString(2, password);
                return ps;
            };

            jtm.update(preparedStatementCreator, keyHolder);
            Number credentialsId = keyHolder.getKey();

            int rowsAffected = jtm.update(insertCustomerSQL,
                    credentialsId,
                    firstName,
                    lastName,
                    email,
                    phone,
                    Date.valueOf(dob)
            );

            if (rowsAffected == 0) {
                result = Either.left(new RestaurantError(Constants.ERROR_ADDING_CUSTOMER));
            } else {
                result = Either.right(0);
            }
        } catch (DuplicateKeyException ex) {
            result = Either.left(new RestaurantError("User exists"));
        }catch (Exception ex) {
            result = Either.left(new RestaurantError(Constants.ERROR_CONNECTING_DATABASE));
        }

        return result;
    }

    @Override
    public Either<RestaurantError, Integer> update(Customer c) {
        Either<RestaurantError, Integer> result = null;
        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());

        try {
            String updateSQL = SQLQueries.UPDATE_CUSTOMERS_SET_FIRST_NAME_LAST_NAME_EMAIL_PHONE_DATE_OF_BIRTH_WHERE_ID;

            String firstName = c.getFirstname();
            String lastName = c.getLastname();
            String email = c.getEmail();
            String phone = c.getPhone();
            LocalDate dob = c.getDob();
            int customerId = c.getId();

            int rowsAffected = jtm.update(updateSQL, firstName, lastName, email, phone, Date.valueOf(dob), customerId);

            if (rowsAffected == 0) {
                result = Either.left(new RestaurantError(Constants.ERROR_UPDATING_CUSTOMER));
            } else {
                result = Either.right(0);
            }
        } catch (Exception ex) {
            result = Either.left(new RestaurantError(Constants.ERROR_CONNECTING_DATABASE));
        }

        return result;
    }

    @Override
    public Either<RestaurantError, Integer> delete(Customer c, boolean deleteOrders) {
        Either<RestaurantError, Integer> result = null;
        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());

        if (!deleteOrders) {
            try {

                String deleteCustomerSQL = SQLQueries.DELETE_FROM_CUSTOMERS_WHERE_ID;
                String deleteCredentialsSQL = SQLQueries.DELETE_FROM_CREDENTIALS_WHERE_CUSTOMER_ID;

                int rowsAffectedCustomer = jtm.update(deleteCustomerSQL, c.getId());
                int rowsAffectedCredentials = jtm.update(deleteCredentialsSQL, c.getId());

                if (rowsAffectedCustomer == 0 || rowsAffectedCredentials == 0) {
                    result = Either.left(new RestaurantError(Constants.ERROR_DELETING_CUSTOMER));
                } else {
                    result = Either.right(0);
                }
            } catch (Exception ex) {
                if (ex.getMessage().contains("foreign key constraint fails")) {
                    result = Either.left(new RestaurantError(Constants.THE_CUSTOMER_HAS_ORDERS));
                } else {
                    result = Either.left(new RestaurantError(Constants.ERROR_DELETING_CUSTOMER));
                }
            }
        } else {
            try {

                backupOrders(orderService.getOrdersByCustomerId(c.getId()));
                String deleteOrdersSQL = SQLQueries.DELETE_FROM_ORDER_ITEMS_WHERE_ORDER_ID_SELECT_ORDER_ID_FROM_ORDERS_WHERE_CUSTOMER_ID;
                String deleteOrdersCustomerSQL = SQLQueries.DELETE_FROM_ORDERS_WHERE_CUSTOMER_ID;
                String deleteCustomerSQL = SQLQueries.DELETE_FROM_CUSTOMERS_WHERE_ID;
                String deleteCredentialsSQL = SQLQueries.DELETE_FROM_CREDENTIALS_WHERE_CUSTOMER_ID;

                jtm.update(deleteOrdersSQL, c.getId());
                jtm.update(deleteOrdersCustomerSQL, c.getId());
                jtm.update(deleteCustomerSQL, c.getId());
                jtm.update(deleteCredentialsSQL, c.getId());

                result = Either.right(0);
            } catch (Exception ex) {
                result = Either.left(new RestaurantError(Constants.ERROR_DELETING_CUSTOMER));
            }
        }

        return result;
    }

    private void backupOrders(List<Order> orders) {
        try {
            Path backupFilePath = Paths.get(Configuration.getInstance().getProperty("pathXmlOrders"));

            JAXBContext context = JAXBContext.newInstance(OrdersXML.class);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            OrdersXML ordersXML = new OrdersXML();
            //ordersXML.setOrders(orders);

            try (FileOutputStream fileOutputStream = new FileOutputStream(backupFilePath.toFile())) {
                marshaller.marshal(ordersXML, fileOutputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
