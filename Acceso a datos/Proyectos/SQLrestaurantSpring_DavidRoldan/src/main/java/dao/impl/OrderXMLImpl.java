package dao.impl;

import io.vavr.control.Either;
import model.Order;
import model.errors.RestaurantError;

import java.util.List;

public class OrderXMLImpl implements dao.OrderDAO{

    @Override
    public Either<RestaurantError, List<Order>> getAll() {
        return null;
    }

    @Override
    public Either<RestaurantError, Order> get(int id) {
        return null;
    }

    @Override
    public Either<RestaurantError, Integer> save(Order c) {
        return null;
    }

    @Override
    public Either<RestaurantError, Integer> update(Order c) {
        return null;
    }

    @Override
    public Either<RestaurantError, Integer> delete(Order c) {
        return null;
    }
}
