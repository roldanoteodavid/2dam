package domain.servicios;

import domain.modelo.Order;
import domain.modelo.errores.ApiError;
import io.vavr.control.Either;

import java.util.List;

public interface OrderService {
    Either<ApiError, List<Order>> getAll();

    Either<ApiError, Order> get(int id);

    Either<ApiError, Integer> save(Order c);

    Either<ApiError, Integer> update(Order c);

    Either<ApiError, Integer> delete(Order c);
}
