package dao;

import io.vavr.control.Either;
import model.Customer;
import model.MenuItem;
import model.Order;
import model.errors.RestaurantError;
import model.hibernate.CredentialHib;
import model.hibernate.CustomerHib;
import model.hibernate.MenuItemHib;
import model.hibernate.OrderHib;

import java.util.List;

public interface HibernateDAO {

    Either<RestaurantError, List<CustomerHib>> getAllCustomers();
    Either<RestaurantError, List<MenuItemHib>> getAllMenuItems();
}
