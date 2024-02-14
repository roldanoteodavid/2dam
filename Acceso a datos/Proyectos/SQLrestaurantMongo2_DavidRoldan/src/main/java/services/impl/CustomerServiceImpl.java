package services.impl;

import common.Constants;
import dao.*;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.Convert;
import lombok.extern.log4j.Log4j2;
import model.*;
import model.errors.RestaurantError;
import model.hibernate.*;
import org.bson.types.ObjectId;
import org.hibernate.Hibernate;
import services.CustomerService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class CustomerServiceImpl implements CustomerService {

    private final CustomersDAO dao;
    private final MenuItemDAO menuItemDAO;
    private final LoginDAO loginDAO;
    private final HibernateDAO hibernateDAO;
    @Inject
    public CustomerServiceImpl(CustomersDAO dao, MenuItemDAO menuItemDAO, LoginDAO loginDAO, HibernateDAO hibernateDAO) {
        this.dao = dao;
        this.menuItemDAO = menuItemDAO;
        this.loginDAO = loginDAO;
        this.hibernateDAO = hibernateDAO;
    }

    @Override
    public Either<RestaurantError, Integer> transferToMongo() {
        try {
            List<CustomerHib> customersHib = hibernateDAO.getAllCustomers().get();
            List<MenuItemHib> menuItemsHib = hibernateDAO.getAllMenuItems().get();
            //List<CredentialHib> credentialsHib = hibernateDAO.getAllCredentials().get();

            // Convertir a modelos de MongoDB
            List<Customer> customers = customersHib.stream().map(this::convertToCustomer).toList();
            List<MenuItem> menuItems = menuItemsHib.stream().map(this::convertToMenuItem).toList();
            //List<Credential> credentials = credentialsHib.stream().map(this::convertToCredential).toList();

            // Guardar en MongoDB
            customersHib.forEach(customerHib -> {
                dao.save(convertToCustomer(customerHib), new Credential(
                        null,
                        customerHib.getCredential().getUser_name(),
                        customerHib.getCredential().getPassword()
                ));
            });
            menuItems.forEach(menuItemDAO::save);

            return Either.right(0);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return Either.left(new RestaurantError(Constants.ERROR_TRANSFERRING_TO_MONGO));
        }
    }

    public Either<RestaurantError, List<Customer>> getAll() {
        return dao.getAll();
    }

    public Either<RestaurantError, Customer> get(ObjectId id) {
        return dao.get(id);
    }

    @Override
    public Either<RestaurantError, Customer> get(LocalDateTime orderdate) {
        return dao.get(orderdate);
    }

    public Either<RestaurantError, Integer> save(Customer c, Credential credential) {
        return dao.save(c, credential);
    }

    public Either<RestaurantError, Integer> update(Customer c) {
        return dao.update(c);
    }

    public Either<RestaurantError, Integer> delete(Customer c, boolean deleteOrders) {
//        if (deleteOrders) {
//            orderXML.save(orderXML.getAll(c.getId()).get());
//        }
        return dao.delete(c, deleteOrders);
    }


    private Customer convertToCustomer(CustomerHib customerHib) {
        Hibernate.initialize(customerHib.getOrders());
        return new Customer(
                null,
                customerHib.getFirstname(),
                customerHib.getLastname(),
                customerHib.getEmail(),
                customerHib.getPhone(),
                customerHib.getDob(),
                convertToOrders(customerHib.getOrders())
        );
    }

    private Order convertToOrder(OrderHib orderHib) {
        return new Order(
                orderHib.getDate(),
                orderHib.getTable_id(),
                convertToOrderItems(orderHib.getOrderItems())
        );
    }

    private OrderItem convertToOrderItem(OrderItemHib orderItemHib) {
        return new OrderItem(
                orderItemHib.getMenuItem().getId(),
                orderItemHib.getQuantity()
        );
    }

    private List<Order> convertToOrders(List<OrderHib> orderHibs) {
        return orderHibs.stream()
                .map(this::convertToOrder)
                .collect(Collectors.toList());
    }

    private List<OrderItem> convertToOrderItems(List<OrderItemHib> orderItemHibs) {
        return orderItemHibs.stream()
                .map(this::convertToOrderItem)
                .collect(Collectors.toList());
    }

    private MenuItem convertToMenuItem(MenuItemHib menuItemHib) {
        return new MenuItem(
                menuItemHib.getId(),
                menuItemHib.getName(),
                menuItemHib.getDescription(),
                menuItemHib.getPrice()
        );
    }

    private Credential convertToCredential(CredentialHib credentialHib) {
        return new Credential(
                null,
                credentialHib.getUser_name(),
                credentialHib.getPassword()
        );
    }
}
