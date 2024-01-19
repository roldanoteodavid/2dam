package services.impl;

import dao.OrderItemDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Order;
import model.OrderItem;
import model.errors.RestaurantError;
import model.xml.OrderItemXML;
import services.OrderItemService;

import java.util.ArrayList;
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
    public Either<RestaurantError, List<OrderItemXML>> get(Order o) {
        return dao.get(o);
    }

    @Override
    public Either<RestaurantError, Integer> save(List<OrderItem> c, Order o) {
        return dao.save(c, o);
    }

    @Override
    public Either<RestaurantError, Integer> update(List<OrderItem> c, Order o) {
        return dao.update(c, o);
    }

    @Override
    public Either<RestaurantError, Integer> delete(Order o) {
        return dao.delete(o);
    }

    @Override
    public List<OrderItem> orderItemsOrderdByQuantity(Order o) {
        List<OrderItem> orderItems = new ArrayList<>();
        dao.getAll(o.getId()).get().forEach(orderItem -> orderItems.add(orderItem));
        if (!orderItems.isEmpty())
            orderItems.sort((o1, o2) -> o2.getQuantity() - o1.getQuantity());
        return orderItems;
    }
}
