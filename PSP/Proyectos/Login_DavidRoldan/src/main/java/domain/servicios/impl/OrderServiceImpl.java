package domain.servicios.impl;

import dao.OrderDAO;
import domain.modelo.Order;
import domain.modelo.errores.ApiError;
import domain.servicios.OrderService;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO dao;

    @Inject
    public OrderServiceImpl(OrderDAO dao) {
        this.dao = dao;
    }

    public Either<ApiError, List<Order>> getAll() {
        return dao.getAll();
    }

    public Either<ApiError, Order> get(int id) {
        return dao.get(id);
    }

    public Either<ApiError, Integer> save(Order o) {
        return dao.save(o);
    }

    public Either<ApiError, Integer> update(Order o) {
        return dao.update(o);
    }

    public Either<ApiError, Integer> delete(Order o) {
        return dao.delete(o);
    }
}
