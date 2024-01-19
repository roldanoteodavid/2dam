package dao.impl;

import common.Constants;
import common.SQLQueries;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import model.MenuItem;
import model.Order;
import model.OrderItem;
import model.errors.RestaurantError;
import model.xml.OrderItemXML;
import model.xml.OrderXML;
import model.xml.OrdersXML;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Named(Constants.XML_ORDER_DAO)
public class OrderXMLImpl implements dao.OrderDAO {
    private JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public OrderXMLImpl(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    @Override
    public Either<RestaurantError, List<Order>> getAll() {
        return null;
    }

    @Override
    public Either<RestaurantError, List<Order>> getAll(int idcustomer) {
        Either<RestaurantError, List<Order>> result = null;
        em = jpaUtil.getEntityManager();
        try {
            List<Order> orders = em.createQuery("FROM Order o WHERE o.customer_id = :customerId", Order.class)
                    .setParameter("customerId", idcustomer)
                    .getResultList();

            if (orders.isEmpty()) {
                result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_ORDERS));
            } else {
                result = Either.right(orders);
            }
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR_CONNECTING_DATABASE));
        } finally {
            if (em != null) em.close();
        }
        return result;
    }

    @Override
    public Either<RestaurantError, Order> get(int id) {
        return null;
    }

    @Override
    public Either<RestaurantError, Integer> save(Order c) {
        return null;
    }

    @Override
    public Either<RestaurantError, Integer> save(List<Order> orders) {
        Either<RestaurantError, Integer> result;

        int customerId = orders.get(0).getCustomer_id();

        Path ordersFilePath = Paths.get(Constants.PATH_DATA + Constants.CUSTOMER + customerId + Constants.PATH_XML_ORDERS);

        try {
            JAXBContext context = JAXBContext.newInstance(OrdersXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            OrdersXML ordersList = new OrdersXML();
            ordersList.setOrders(new ArrayList<>());

            for (Order order : orders) {
                OrderXML newOrder = new OrderXML();
                newOrder.setId(order.getId());
                newOrder.setOrderItems(new ArrayList<>());

                order.getOrderItems().forEach(orderItem -> {
                    OrderItemXML orderItemXML = new OrderItemXML(orderItem.getMenuItem().getName(), orderItem.getQuantity());
                    newOrder.getOrderItems().add(orderItemXML);
                });

                ordersList.getOrders().add(newOrder);
            }

            marshaller.marshal(ordersList, Files.newOutputStream(ordersFilePath));
            result = Either.right(0);
        } catch (JAXBException | IOException e) {
            result = Either.left(new RestaurantError("Error reading/writing the file"));
        }

        return result;
    }

    @Override
    public Either<RestaurantError, Integer> update(Order c) {
        return null;
    }

    @Override
    public Either<RestaurantError, Integer> delete(Order c) {
        return null;
    }
}
