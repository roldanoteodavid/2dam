package services;

import io.vavr.control.Either;
import model.Order;
import model.errors.RestaurantError;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    Either<RestaurantError, List<Order>> getAll();

    Either<RestaurantError, Order> get(int id);

    Either<RestaurantError, Integer> save(Order c, ObjectId id);

    Either<RestaurantError, Integer> update(Order c);

    Either<RestaurantError, Integer> delete(Order c);

    List<Order> getOrdersByCustomerId(ObjectId id);
    List<Order> getOrdersByDate(LocalDate date);
    List<Order> getOrdersByDateAndCustomerId(LocalDate date, ObjectId id);
    boolean hasOrder(ObjectId id);
}
