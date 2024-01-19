package services.impl;

import common.Constants;
import dao.CustomersDAO;
import dao.OrderDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import model.Customer;
import model.errors.RestaurantError;
import services.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final CustomersDAO dao;
    private final OrderDAO orderXML;
    @Inject
    public CustomerServiceImpl(CustomersDAO dao, @Named(Constants.XML_ORDER_DAO) OrderDAO orderXML) {
        this.dao = dao;
        this.orderXML = orderXML;
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
        if (deleteOrders) {
            orderXML.save(orderXML.getAll(c.getId()).get());
        }
        return dao.delete(c, deleteOrders);
    }
}
