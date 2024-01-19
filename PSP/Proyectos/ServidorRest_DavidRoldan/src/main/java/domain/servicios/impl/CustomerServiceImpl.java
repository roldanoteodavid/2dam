package domain.servicios.impl;

import dao.CustomersDAO;
import domain.modelo.Customer;
import domain.modelo.errores.RestaurantError;
import domain.servicios.CustomerService;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final CustomersDAO dao;
    @Inject
    @Named("CustomerDAOFile")
    public CustomerServiceImpl(CustomersDAO dao) {
        this.dao = dao;
    }

    public Either<RestaurantError, List<Customer>> getAll() {
        return dao.getAll();
    }

    public Either<RestaurantError, Customer> get(int id) {
        return dao.get(id);
    }

    public Either<RestaurantError, Integer> save(Customer c) {
        return dao.save(c);
    }

    public Either<RestaurantError, Integer> update(Customer c) {
        return dao.update(c);
    }

    public Either<RestaurantError, Integer> delete(Customer c, boolean deleteOrders) {
        return dao.delete(c, deleteOrders);
    }
}
