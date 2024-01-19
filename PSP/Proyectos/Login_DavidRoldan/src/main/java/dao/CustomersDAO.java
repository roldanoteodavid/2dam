package dao;

import domain.modelo.Customer;
import domain.modelo.errores.ApiError;
import io.vavr.control.Either;

import java.util.List;

public interface CustomersDAO {

    Either<ApiError, List<Customer>> getAll();

    Either<ApiError, Customer> get(int id);

    Either<ApiError, Integer> save(Customer c);

    Either<ApiError, Integer> update(Customer c);

    Either<ApiError, Integer> delete(Customer c);
}
