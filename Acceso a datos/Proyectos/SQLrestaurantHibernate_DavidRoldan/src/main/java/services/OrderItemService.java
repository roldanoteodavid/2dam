package services;

import io.vavr.control.Either;
import model.Order;
import model.OrderItem;
import model.errors.RestaurantError;

import java.util.List;

public interface OrderItemService {
    Either<RestaurantError, List<OrderItem>> getAll(Order o);

    Either<RestaurantError, OrderItem> get(OrderItem o);

    Either<RestaurantError, Integer> save(List<OrderItem> c);

    Either<RestaurantError, Integer> update(List<OrderItem> c);

    Either<RestaurantError, Integer> delete(Order o);

    double getTotalPrice(List<OrderItem> orderItems);
}
