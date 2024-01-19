package dao;

import io.vavr.control.Either;
import model.Order;
import model.OrderItem;
import model.errors.RestaurantError;

import java.util.List;

public interface OrderItemDAO {
    Either<RestaurantError, List<OrderItem>> getAll(int idOrder);

    Either<RestaurantError, List<OrderItem>> get(Order o);

    Either<RestaurantError, Integer> save(List<OrderItem> c);

    Either<RestaurantError, Integer> update(List<OrderItem> c);

    Either<RestaurantError, Integer> delete(Order o);
}
