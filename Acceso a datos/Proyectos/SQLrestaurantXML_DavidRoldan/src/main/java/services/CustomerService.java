package services;

import io.vavr.control.Either;
import model.Customer;
import model.errors.CustomerError;

import java.util.List;

public interface CustomerService {
    Either<CustomerError, List<Customer>> getAll();

    Either<CustomerError, Customer> get(int id);

    Either<CustomerError, Integer> save(Customer c);

    Either<CustomerError, Integer> update(Customer c);

    Either<CustomerError, Integer> delete(Customer c);
}
