package dao.impl;

import common.Constants;
import dao.CustomersDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Credential;
import model.Customer;
import model.Order;
import model.errors.RestaurantError;

import java.util.List;

public class CustomerDaoImpl implements CustomersDAO {

    private JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public CustomerDaoImpl(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    @Override
    public Either<RestaurantError, List<Customer>> getAll() {
        Either<RestaurantError, List<Customer>> result = null;
        em = jpaUtil.getEntityManager();
        try {
            List<Customer> customers = em.createQuery("from Customer", Customer.class).getResultList();
            result = Either.right(customers);
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_CUSTOMERS));
        } finally {
            if (em != null) em.close();
        }
        return result;
    }

    @Override
    public Either<RestaurantError, Customer> get(int id) {
        Either<RestaurantError, Customer> result = null;
        em = jpaUtil.getEntityManager();
        try {
            Customer customer = em.find(Customer.class, id);
            result = Either.right(customer);
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_CUSTOMER));
        } finally {
            if (em != null) em.close();
        }
        return result;
    }

    @Override
    public Either<RestaurantError, Integer> save(Customer c) {
        Either<RestaurantError, Integer> result;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Credential credential = c.getCredential();
            em.persist(credential);
            c.setId(credential.getId());
            em.persist(c);
            tx.commit();
            result = Either.right(0);
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            result = Either.left(new RestaurantError(Constants.ERROR_ADDING_CUSTOMER));
        } finally {
            if (em != null) em.close();
        }

        return result;
    }


    @Override
    public Either<RestaurantError, Integer> update(Customer c) {
        Either<RestaurantError, Integer> result = null;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(c);
            tx.commit();
            result = Either.right(0);
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            result = Either.left(new RestaurantError(Constants.ERROR_UPDATING_CUSTOMER));
        } finally {
            if (em != null) em.close();
        }

        return result;
    }

    @Override
    public Either<RestaurantError, Integer> delete(Customer c, boolean deleteOrders) {
        Either<RestaurantError, Integer> result = null;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            if (deleteOrders) {
                List<Order> orders = em.createQuery("SELECT o FROM Order o WHERE o.customer_id = :customerId", Order.class)
                        .setParameter("customerId", c.getId())
                        .getResultList();

                for (Order order : orders) {
                    em.createQuery("DELETE FROM OrderItem oi WHERE oi.order.id = :orderId")
                            .setParameter("orderId", order.getId())
                            .executeUpdate();
                    em.remove(em.merge(order));
                }
            }
            em.remove(em.merge(c));
            tx.commit();
            result = Either.right(0);
        } catch (Exception ex) {

            if (ex.getMessage().contains(Constants.FOREIGN_KEY_CONSTRAINT_FAILS)) {
                result = Either.left(new RestaurantError(Constants.THE_CUSTOMER_HAS_ORDERS));
            } else {
                result = Either.left(new RestaurantError(Constants.ERROR_DELETING_CUSTOMER));
            }
        } finally {
            if (em != null) em.close();
        }

        return result;
    }


}
