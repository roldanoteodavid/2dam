package services;

import io.vavr.control.Either;
import model.Order;
import model.OrderItem;
import model.errors.OrderError;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    Either<OrderError, List<Order>> getAll();

    Either<OrderError, Order> get(int id);

    Either<OrderError, Integer> save(Order c);

    Either<OrderError, Integer> update(Order c);

    Either<OrderError, Integer> delete(Order c);

    List<Order> getOrdersByCustomerId(int id);
    List<Order> getOrdersByDate(LocalDate date);
    int newId();

    void deleteOrdersByCustomerId(int id);
}
