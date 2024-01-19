package dao;

import domain.modelo.Customer;
import domain.modelo.errores.RestaurantError;
import io.vavr.control.Either;

import java.util.List;

public interface CustomersDAO {

    Either<RestaurantError, List<Customer>> getAll();

    Either<RestaurantError, Customer> get(int id);

    Either<RestaurantError, Integer> save(Customer c);

    Either<RestaurantError, Integer> update(Customer c);

    Either<RestaurantError, Integer> delete(Customer c, boolean deleteOrders);
}
