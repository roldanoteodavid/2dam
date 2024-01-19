package dao;

import io.vavr.control.Either;
import model.Order;
import model.errors.RestaurantError;

import java.util.List;

public interface OrderDAO {
    Either<RestaurantError, List<Order>> getAll();
    Either<RestaurantError, List<Order>> getAll(int idcustomer);

    Either<RestaurantError, Order> get(int id);

    Either<RestaurantError, Integer> save(Order c);
    Either<RestaurantError, Integer> save(List<Order> c);

    Either<RestaurantError, Integer> update(Order c);

    Either<RestaurantError, Integer> delete(Order c);
}
