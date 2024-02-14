package dao;

import io.vavr.control.Either;
import model.Credential;
import model.Customer;
import model.errors.RestaurantError;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomersDAO {

    Either<RestaurantError, List<Customer>> getAll();

    Either<RestaurantError, Customer> get(ObjectId id);
    Either<RestaurantError, Customer> get(LocalDateTime orderdate);

    Either<RestaurantError, Integer> save(Customer c, Credential credential);

    Either<RestaurantError, Integer> update(Customer c);

    Either<RestaurantError, Integer> delete(Customer c, boolean deleteOrders);
}
