package domain.servicios.impl;

import dao.CustomersDAO;
import domain.modelo.Customer;
import domain.modelo.errores.ApiError;
import domain.servicios.CustomerService;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final CustomersDAO dao;

    @Inject
    public CustomerServiceImpl(CustomersDAO dao) {
        this.dao = dao;
    }

    public Either<ApiError, List<Customer>> getAll() {
        return dao.getAll();
    }

    public Either<ApiError, Customer> get(int id) {
        return dao.get(id);
    }

    public Either<ApiError, Integer> save(Customer c) {
        return dao.save(c);
    }

    public Either<ApiError, Integer> update(Customer c) {
        return dao.update(c);
    }

    public Either<ApiError, Integer> delete(Customer c) {
        return dao.delete(c);
    }
}
