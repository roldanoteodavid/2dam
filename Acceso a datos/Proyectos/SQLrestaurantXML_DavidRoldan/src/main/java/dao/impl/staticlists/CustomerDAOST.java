package dao.impl.staticlists;


import io.vavr.control.Either;
import model.Customer;
import model.errors.CustomerError;
import model.errors.CustomerErrorEmptyList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOST {

    public Either<CustomerError, List<Customer>> getAll() {
        Either<CustomerError, List<Customer>> result;

        //Fill with data
        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer(1, "Juan", "Perez", "juanperez@gmail.com", "603204882", LocalDate.of(1990, 2, 20)));
        customerList.add(new Customer(2, "María", "Sánchez", "maria.sanchez@example.com", "+34 645 234 567", LocalDate.of(1990, 3, 20)));
        customerList.add(new Customer(3, "Carlos", "López", "carlos.lopez@example.com", "+34 612 345 678", LocalDate.of(1982, 8, 10)));
        customerList.add(new Customer(4, "Ana", "Martínez", "ana.martinez@example.com", "+34 688 456 789", LocalDate.of(1993, 11, 25)));
        customerList.add(new Customer(5, "Pedro", "Rodríguez", "pedro.rodriguez@example.com", "+34 611 567 890", LocalDate.of(1978, 7, 5)));
        customerList.add(new Customer(6, "Juan", "Gómez", "juan.gomez@example.com", "+34 600 123 456", LocalDate.of(1985, 5, 15)));
        if (customerList.isEmpty()) result = Either.left(new CustomerErrorEmptyList());
        else result = Either.right(customerList);
        return result;
    }


    public Either<CustomerError, Customer> get(int id) {
        return null;
    }


    public Either<CustomerError, Integer> save(Customer c) {
        return null;
    }


    public Either<CustomerError, Integer> update(Customer c) {
        return null;
    }


    public Either<CustomerError, Integer> delete(Customer c) {
        return null;
    }
}
