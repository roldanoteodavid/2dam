package dao;

import domain.modelo.Order;
import domain.modelo.errores.RestaurantError;
import io.vavr.control.Either;

import java.util.List;

public interface OrderDAO {
    Either<RestaurantError, List<Order>> getAll();

    Either<RestaurantError, Order> get(int id);

    Either<RestaurantError, Integer> save(Order c);

    Either<RestaurantError, Integer> update(Order c);

    Either<RestaurantError, Integer> delete(Order c);
}
