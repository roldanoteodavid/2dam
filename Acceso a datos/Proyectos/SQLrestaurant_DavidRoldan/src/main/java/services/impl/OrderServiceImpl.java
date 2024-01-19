package services.impl;

import dao.OrderDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import model.Order;
import model.errors.OrderError;
import services.OrderService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO dao;

    @Inject
    @Named("OrderDAOFile")
    public OrderServiceImpl(OrderDAO dao) {
        this.dao = dao;
    }

    public Either<OrderError, List<Order>> getAll() {
        return dao.getAll();
    }

    public Either<OrderError, Order> get(int id) {
        return dao.get(id);
    }

    public Either<OrderError, Integer> save(Order o) {
        return dao.save(o);
    }

    public Either<OrderError, Integer> update(Order o) {
        return dao.update(o);
    }

    public Either<OrderError, Integer> delete(Order o) {
        return dao.delete(o);
    }

    @Override
    public List<Order> getOrdersByCustomerId(int id) {
        Either<OrderError, List<Order>> result = dao.getAll();

        if (result.isLeft()) {
            return Collections.emptyList();
        } else {
            List<Order> allOrders = result.get();
            List<Order> customerOrders = allOrders.stream()
                    .filter(order -> order.getCustomer_id() == id)
                    .collect(Collectors.toList());
            return customerOrders;
        }
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate date) {
        Either<OrderError, List<Order>> result = dao.getAll();
        if (result.isLeft()) {
            return Collections.emptyList();
        } else {
            List<Order> allOrders = result.get();
            List<Order> ordersByDate = allOrders.stream()
                    .filter(order -> order.getDate().toLocalDate().equals(date))
                    .collect(Collectors.toList());
            return ordersByDate;
        }
    }

    @Override
    public void deleteOrdersByCustomerId(int id) {
        List<Order> orders = getOrdersByCustomerId(id);
        orders.forEach(this::delete);
    }
}
