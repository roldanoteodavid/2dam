package dao.impl;

import common.Constants;
import dao.HibernateDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import model.errors.RestaurantError;
import model.hibernate.CredentialHib;
import model.hibernate.CustomerHib;
import model.hibernate.MenuItemHib;
import model.hibernate.OrderHib;
import org.hibernate.Hibernate;

import java.util.List;

public class HibernateDaoImpl implements HibernateDAO {
    private JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public HibernateDaoImpl(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    @Override
    public Either<RestaurantError, List<CustomerHib>> getAllCustomers() {
        Either<RestaurantError, List<CustomerHib>> result = null;
        try {
            em = jpaUtil.getEntityManager();
            List<CustomerHib> customers = em.createQuery("from CustomerHib", CustomerHib.class).getResultList();
            for (CustomerHib customer : customers) {
                Hibernate.initialize(customer.getOrders());
                for (OrderHib order : customer.getOrders()) {
                    Hibernate.initialize(order.getOrderItems());
                }
            }
            result = Either.right(customers);
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_CUSTOMERS));
        } finally {
            if (em != null) em.close();
        }
        return result;
    }

    @Override
    public Either<RestaurantError, List<MenuItemHib>> getAllMenuItems() {
        Either<RestaurantError, List<MenuItemHib>> result = null;
        try {
            em = jpaUtil.getEntityManager();
            List<MenuItemHib> menuItems = em.createQuery("from MenuItemHib", MenuItemHib.class).getResultList();
            result = Either.right(menuItems);
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_MENU_ITEMS));
        } finally {
            if (em != null) em.close();
        }
        return result;
    }
}