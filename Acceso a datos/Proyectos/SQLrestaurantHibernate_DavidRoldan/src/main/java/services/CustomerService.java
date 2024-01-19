package services;

import io.vavr.control.Either;
import model.Customer;
import model.errors.RestaurantError;

import java.util.List;

public interface CustomerService {
    Either<RestaurantError, List<Customer>> getAll();

    Either<RestaurantError, Customer> get(int id);

    Either<RestaurantError, Integer> save(Customer c);

    Either<RestaurantError, Integer> update(Customer c);

    Either<RestaurantError, Integer> delete(Customer c, boolean deleteOrders);
}
