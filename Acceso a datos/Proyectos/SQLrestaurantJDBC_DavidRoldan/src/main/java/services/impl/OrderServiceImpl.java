package services.impl;

import dao.OrderDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Order;
import model.errors.RestaurantError;
import services.OrderService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO dao;

    @Inject
    public OrderServiceImpl(OrderDAO dao) {
        this.dao = dao;
    }

    public Either<RestaurantError, List<Order>> getAll() {
        return dao.getAll();
    }

    public Either<RestaurantError, Order> get(int id) {
        return dao.get(id);
    }

    public Either<RestaurantError, Integer> save(Order o) {
        return dao.save(o);
    }

    public Either<RestaurantError, Integer> update(Order o) {
        return dao.update(o);
    }

    public Either<RestaurantError, Integer> delete(Order o) {
        return dao.delete(o);
    }

    @Override
    public List<Order> getOrdersByCustomerId(int id) {
        Either<RestaurantError, List<Order>> result = dao.getAll();

        if (result.isLeft()) {
            return Collections.emptyList();
        } else {
            List<Order> allOrders = result.get();
            return allOrders.stream()
                    .filter(order -> order.getCustomer_id() == id)
                    .collect(Collectors.toList());
        }
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
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<Order> getOrdersByDateAndCustomerId(LocalDate date, int id) {
        Either<RestaurantError, List<Order>> result = dao.getAll();
        if (result.isLeft()) {
            return Collections.emptyList();
        } else {
            List<Order> allOrders = result.get();
            return allOrders.stream()
                    .filter(order -> order.getDate().toLocalDate().equals(date) && order.getCustomer_id() == id)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public int newId() {
        List<Order> orders = dao.getAll().get();
        orders.sort(Comparator.comparing(Order::getId));
        return orders.get(orders.size() - 1).getId() + 1;
    }

    @Override
    public void deleteOrdersByCustomerId(int id) {
        List<Order> orders = getOrdersByCustomerId(id);
        orders.forEach(this::delete);
    }
}
