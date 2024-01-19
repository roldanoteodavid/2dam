package services;

import io.vavr.control.Either;
import model.Order;
import model.errors.RestaurantError;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    Either<RestaurantError, List<Order>> getAll();

    Either<RestaurantError, Order> get(int id);

    Either<RestaurantError, Integer> save(Order c);

    Either<RestaurantError, Integer> update(Order c);

    Either<RestaurantError, Integer> delete(Order c);

    List<Order> getOrdersByCustomerId(int id);
    List<Order> getOrdersByDate(LocalDate date);
    List<Order> getOrdersByDateAndCustomerId(LocalDate date, int id);
    int newId();

    void deleteOrdersByCustomerId(int id);
}
