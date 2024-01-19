package dao.impl;

import common.Constants;
import common.SQLQueries;
import dao.CustomersDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Customer;
import model.errors.RestaurantError;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoJDBC implements CustomersDAO {

    private DBConnectionPool db;

    @Inject
    public CustomerDaoJDBC(DBConnectionPool db) {
        this.db = db;
    }

    @Override
    public Either<RestaurantError, List<Customer>> getAll() {
        Either<RestaurantError, List<Customer>> result = null;
        try (Connection con = db.getConnection();
             Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = stmt.executeQuery(SQLQueries.SELECT_FROM_CUSTOMERS);
            result = Either.right(readRS(rs));
        } catch (SQLException ex) {
            result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_CUSTOMERS));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, Customer> get(int id) {
        Either<RestaurantError, Customer> result = null;
        try (Connection con = db.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.SELECT_FROM_CUSTOMERS_WHERE_ID)) {
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            Customer customer = readRS(rs).get(0);
            if (customer == null) {
                result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_CUSTOMER));
            } else {
                result = Either.right(customer);
            }
        } catch (SQLException ex) {
            result = Either.left(new RestaurantError(Constants.ERROR_CONNECTING_DATABASE));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, Integer> save(Customer c) {
        Either<RestaurantError, Integer> result;
        Connection con = null;
        try {
            con = db.getConnection();
            con.setAutoCommit(false);

            PreparedStatement preparedStatementCredentials = con.prepareStatement(SQLQueries.INSERT_INTO_CREDENTIALS_USERNAME_PASSWORD_VALUES, Statement.RETURN_GENERATED_KEYS);
            preparedStatementCredentials.setString(1, c.getFirstname());
            preparedStatementCredentials.setString(2, c.getFirstname().toLowerCase());
            int rscred = preparedStatementCredentials.executeUpdate();

            if (rscred == 0) {
                con.rollback();
                result = Either.left(new RestaurantError(Constants.ERROR_ADDING_CREDENTIALS));
            } else {
                ResultSet generatedKeys = preparedStatementCredentials.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int credentialsId = generatedKeys.getInt(1);

                    PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.INSERT_INTO_CUSTOMERS_ID_FIRST_NAME_LAST_NAME_EMAIL_PHONE_DATE_OF_BIRTH_VALUES);
                    preparedStatement.setInt(1, credentialsId);
                    preparedStatement.setString(2, c.getFirstname());
                    preparedStatement.setString(3, c.getLastname());
                    preparedStatement.setString(4, c.getEmail());
                    preparedStatement.setString(5, c.getPhone());
                    preparedStatement.setDate(6, Date.valueOf(c.getDob()));

                    int rs = preparedStatement.executeUpdate();

                    if (rs == 0) {
                        con.rollback();
                        result = Either.left(new RestaurantError(Constants.ERROR_ADDING_CUSTOMER));
                    } else {
                        con.commit();
                        result = Either.right(0);
                    }
                } else {
                    con.rollback();
                    result = Either.left(new RestaurantError(Constants.ERROR_ADDING_CUSTOMER));
                }
            }
        } catch (SQLException ex) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException rollbackEx) {
                    result = Either.left(new RestaurantError(Constants.ERROR_CONNECTING_DATABASE));
                }
            }
            result = Either.left(new RestaurantError(Constants.ERROR_CONNECTING_DATABASE));
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException closeEx) {
                    result = Either.left(new RestaurantError(Constants.ERROR_CONNECTING_DATABASE));
                }
            }
        }
        return result;
    }

    @Override
    public Either<RestaurantError, Integer> update(Customer c) {
        Either<RestaurantError, Integer> result;
        try (Connection con = db.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.UPDATE_CUSTOMERS_SET_FIRST_NAME_LAST_NAME_EMAIL_PHONE_DATE_OF_BIRTH_WHERE_ID)) {
            preparedStatement.setString(1, c.getFirstname());
            preparedStatement.setString(2, c.getLastname());
            preparedStatement.setString(3, c.getEmail());
            preparedStatement.setString(4, c.getPhone());
            preparedStatement.setDate(5, Date.valueOf(c.getDob()));
            preparedStatement.setInt(6, c.getId());

            int rs = preparedStatement.executeUpdate();

            if (rs == 0) {
                result = Either.left(new RestaurantError(Constants.ERROR_UPDATING_CUSTOMER));
            } else {
                result = Either.right(0);
            }
        } catch (SQLException ex) {
            result = Either.left(new RestaurantError(Constants.ERROR_CONNECTING_DATABASE));
        }

        return result;
    }

    @Override
    public Either<RestaurantError, Integer> delete(Customer c, boolean deleteOrders) {
        Either<RestaurantError, Integer> result;
        Connection con = null;
        try {
            con = db.getConnection();
            con.setAutoCommit(false);
            if (!deleteOrders) {
                try (PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.DELETE_FROM_CUSTOMERS_WHERE_ID);
                     PreparedStatement preparedStatementCredentials = con.prepareStatement(SQLQueries.DELETE_FROM_CREDENTIALS_WHERE_CUSTOMER_ID)) {
                    preparedStatement.setInt(1, c.getId());
                    preparedStatementCredentials.setInt(1, c.getId());
                    int rs = preparedStatement.executeUpdate();
                    preparedStatementCredentials.executeUpdate();

                    if (rs == 0) {
                        result = Either.left(new RestaurantError(Constants.ERROR_DELETING_CUSTOMER));
                    } else {
                        result = Either.right(0);
                    }
                    con.commit();
                } catch (SQLException ex) {
                    con.rollback();
                    if (ex.getErrorCode() == 1451) {
                        result = Either.left(new RestaurantError(Constants.THE_CUSTOMER_HAS_ORDERS));
                    } else {
                        result = Either.left(new RestaurantError(Constants.ERROR_CONNECTING_DATABASE));
                    }
                }
            } else {
                try (PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.DELETE_FROM_ORDER_ITEMS_WHERE_ORDER_ID_SELECT_ORDER_ID_FROM_ORDERS_WHERE_CUSTOMER_ID);
                     PreparedStatement preparedStatementDeleteOrders = con.prepareStatement(SQLQueries.DELETE_FROM_ORDERS_WHERE_CUSTOMER_ID);
                     PreparedStatement preparedStatementCredentials = con.prepareStatement(SQLQueries.DELETE_FROM_CREDENTIALS_WHERE_CUSTOMER_ID);
                     PreparedStatement preparedStatementCustomer = con.prepareStatement(SQLQueries.DELETE_FROM_CUSTOMERS_WHERE_ID)) {
                    preparedStatement.setInt(1, c.getId());
                    preparedStatementDeleteOrders.setInt(1, c.getId());
                    preparedStatement.executeUpdate();
                    preparedStatementDeleteOrders.executeUpdate();
                    preparedStatementCustomer.setInt(1, c.getId());
                    preparedStatementCredentials.setInt(1, c.getId());

                    int rs = preparedStatementCustomer.executeUpdate();
                    preparedStatementCredentials.executeUpdate();

                    if (rs == 0) {
                        result = Either.left(new RestaurantError(Constants.ERROR_DELETING_CUSTOMER));
                    } else {
                        result = Either.right(0);
                    }
                    con.commit();
                } catch (SQLException ex) {
                    con.rollback();
                    result = Either.left(new RestaurantError(Constants.ERROR_CONNECTING_DATABASE));
                }
            }
        } catch (SQLException e) {
            result = Either.left(new RestaurantError(Constants.ERROR_CONNECTING_DATABASE));
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException closeEx) {
                    result = Either.left(new RestaurantError(Constants.ERROR_CONNECTING_DATABASE));
                }
            }
        }
        return result;
    }

    private List<Customer> readRS(ResultSet rs) {
        List<Customer> customers = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt(Constants.ID);
                String firstName = rs.getString(Constants.FIRST_NAME);
                String lastName = rs.getString(Constants.LAST_NAME);
                String email = rs.getString(Constants.EMAIL);
                String phone = rs.getString(Constants.PHONE);
                LocalDate birthDate = rs.getDate(Constants.DATE_OF_BIRTH).toLocalDate();
                customers.add(new Customer(id, firstName, lastName, email, phone, birthDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
