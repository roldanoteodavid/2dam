package services.impl;

import dao.CustomersDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import model.Customer;
import model.errors.CustomerError;
import services.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final CustomersDAO dao;
    @Inject
    @Named("CustomerDAOFile")
    public CustomerServiceImpl(CustomersDAO dao) {
        this.dao = dao;
    }

    public Either<CustomerError, List<Customer>> getAll() {
        return dao.getAll();
    }

    public Either<CustomerError, Customer> get(int id) {
        return dao.get(id);
    }

    public Either<CustomerError, Integer> save(Customer c) {
        return dao.save(c);
    }

    public Either<CustomerError, Integer> update(Customer c) {
        return dao.update(c);
    }

    public Either<CustomerError, Integer> delete(Customer c) {
        return dao.delete(c);
    }
}
