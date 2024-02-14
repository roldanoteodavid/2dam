package services.impl;

import dao.OrderItemDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Order;
import model.OrderItem;
import model.errors.RestaurantError;
import services.MenuItemService;
import services.OrderItemService;

import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemDAO dao;
    private final MenuItemService menuItemService;

    @Inject
    public OrderItemServiceImpl(OrderItemDAO dao, MenuItemService menuItemService) {
        this.dao = dao;
        this.menuItemService = menuItemService;
    }

    @Override
    public Either<RestaurantError, List<OrderItem>> getAll(Order o) {
        return dao.getAll(o);
    }

    @Override
    public Either<RestaurantError, OrderItem> get(OrderItem o) {
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
    public double getTotalPrice(List<OrderItem> orderItems) {
        double totalPrice = orderItems.stream().mapToDouble(orderItem -> orderItem.getQuantity() * menuItemService.get(orderItem.getMenu_item_id()).get().getPrice()).sum();
        return Math.round(totalPrice * 100.0) / 100.0;
    }


}
