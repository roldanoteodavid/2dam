package services.impl;

import common.Constants;
import dao.CustomersDAO;
import dao.OrderDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import model.Customer;
import model.Order;
import model.errors.RestaurantError;
import org.bson.types.ObjectId;
import services.OrderService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO dao;
    private final CustomersDAO customersDAO;

    @Inject
    public OrderServiceImpl(@Named(Constants.JDBC_ORDER_DAO) OrderDAO dao, CustomersDAO customersDAO) {
        this.dao = dao;
        this.customersDAO = customersDAO;
    }

    public Either<RestaurantError, List<Order>> getAll() {
        return dao.getAll();
    }

    public Either<RestaurantError, Order> get(int id) {
        return dao.get(id);
    }

    public Either<RestaurantError, Integer> save(Order o, ObjectId id) {
        return dao.save(o, id);
    }

    public Either<RestaurantError, Integer> update(Order o) {
        return dao.update(o);
    }

    public Either<RestaurantError, Integer> delete(Order o) {
        return dao.delete(o);
    }

    @Override
    public List<Order> getOrdersByCustomerId(ObjectId id) {
        Customer customer = customersDAO.get(id).get();
        if (customer.getOrders() == null || customer.getOrders().isEmpty()) {
            return Collections.emptyList();
        }
        return customer.getOrders();
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate date) {
        Either<RestaurantError, List<Order>> result = dao.getAll();
        if (result.isLeft()) {
            return Collections.emptyList();
        } else {
            List<Order> allOrders = result.get();
            return allOrders.stream()
                    .filter(order -> order.getDate().toLocalDate().equals(date))
                    .toList();
        }
    }

    @Override
    public List<Order> getOrdersByDateAndCustomerId(LocalDate date, ObjectId id) {
        Either<RestaurantError, Customer> result = customersDAO.get(id);
        if (result.isLeft()) {
            return Collections.emptyList();
        } else {
            List<Order> allOrders = result.get().getOrders();
            if (allOrders == null || allOrders.isEmpty()) {
                return Collections.emptyList();
            }
            return allOrders.stream()
                    .filter(order -> order.getDate().toLocalDate().equals(date))
                    .toList();
        }
    }

    @Override
    public boolean hasOrder(ObjectId id) {
        Customer customer = customersDAO.get(id).get();
        return customer.getOrders() != null && !customer.getOrders().isEmpty();
    }
}
