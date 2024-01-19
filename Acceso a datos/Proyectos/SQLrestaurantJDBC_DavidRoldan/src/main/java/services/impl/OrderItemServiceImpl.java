package services.impl;

import dao.OrderItemDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Order;
import model.OrderItem;
import model.errors.RestaurantError;
import services.OrderItemService;

import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemDAO dao;

    @Inject
    public OrderItemServiceImpl(OrderItemDAO dao) {
        this.dao = dao;
    }

    @Override
    public Either<RestaurantError, List<OrderItem>> getAll(int idOrder) {
        return dao.getAll(idOrder);
    }

    @Override
    public Either<RestaurantError, List<OrderItem>> get(Order o) {
        return dao.get(o);
    }

    @Override
    public Either<RestaurantError, Integer> save(List<OrderItem> c) {
        return dao.save(c);
    }

    @Override
    public Either<RestaurantError, Integer> update(List<OrderItem> c) {
        return dao.update(c);
    }

    @Override
    public Either<RestaurantError, Integer> delete(Order o) {
        return dao.delete(o);
    }

    @Override
    public Integer getTotalPrice(List<OrderItem> orderItems) {
        return orderItems.stream()
                .mapToInt(orderItem -> orderItem.getMenuItem().getPrice())
                .sum();
    }


}
