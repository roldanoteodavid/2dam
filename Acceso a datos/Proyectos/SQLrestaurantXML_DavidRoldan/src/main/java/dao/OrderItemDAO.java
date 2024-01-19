package dao;

import io.vavr.control.Either;
import model.Order;
import model.OrderItem;
import model.errors.OrderError;
import model.errors.RestaurantError;
import model.xml.OrderItemXML;
import model.xml.OrderXML;

import java.util.List;

public interface OrderItemDAO {
    Either<RestaurantError, List<OrderItem>> getAll(int idOrder);

    Either<RestaurantError, List<OrderItemXML>> get(Order o);

    Either<RestaurantError, Integer> save(List<OrderItem> c, Order o);

    Either<RestaurantError, Integer> update(List<OrderItem> c, Order o);

    Either<RestaurantError, Integer> delete(Order o);
}
