package dao.impl;

import common.Constants;
import common.SQLQueries;
import dao.OrderDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Order;
import model.OrderItem;
import model.errors.RestaurantError;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.List;


@Named(Constants.JDBC_ORDER_DAO)
public class OrderDaoImpl implements OrderDAO {


    private JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public OrderDaoImpl(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    @Override
    public Either<RestaurantError, List<Order>> getAll() {
        Either<RestaurantError, List<Order>> result = null;
        em = jpaUtil.getEntityManager();
        try {
            List<Order> orders = em.createQuery("FROM Order", Order.class).getResultList();
            result = Either.right(orders);
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_ORDERS));
        } finally {
            if (em != null) em.close();
        }
        return result;
    }

    @Override
    public Either<RestaurantError, List<Order>> getAll(int idcustomer) {
        return null;
    }

    @Override
    public Either<RestaurantError, Order> get(int id) {
        Either<RestaurantError, Order> result = null;
        em = jpaUtil.getEntityManager();
        try {
            Order order = em.find(Order.class, id);
            result = Either.right(order);
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_ORDER));
        } finally {
            if (em != null) em.close();
        }
        return result;
    }

    @Override
    public Either<RestaurantError, Integer> save(Order c) {
        Either<RestaurantError, Integer> result;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            List<OrderItem> orderItems = c.getOrderItems();
            c.setOrderItems(null);
            em.persist(c);
            for (OrderItem orderItem : orderItems) {
                orderItem.setOrder(c);
                em.persist(orderItem);
            }
            tx.commit();
            result = Either.right(0);
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            result = Either.left(new RestaurantError(Constants.ERROR_SAVING_ORDER+"\n"+e.getMessage()));
        } finally {
            if (em != null) em.close();
        }
        return result;
    }

    @Override
    public Either<RestaurantError, Integer> save(List<Order> c) {
        return null;
    }

    @Override
    public Either<RestaurantError, Integer> update(Order c) {
        Either<RestaurantError, Integer> result;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            em.createQuery("DELETE FROM OrderItem oi WHERE oi.order.id = :orderId")
                    .setParameter("orderId", c.getId())
                    .executeUpdate();

            for (OrderItem orderItem : c.getOrderItems()) {
                em.persist(em.contains(orderItem) ? orderItem : em.merge(orderItem));
            }

            c.setOrderItems(null);

            em.merge(c);

            tx.commit();
            result = Either.right(0);
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            result = Either.left(new RestaurantError(Constants.ERROR_UPDATING_ORDER + e.getMessage()));
        } finally {
            if (em != null) em.close();
        }
        return result;
    }

    @Override
    public Either<RestaurantError, Integer> delete(Order c) {
        Either<RestaurantError, Integer> result = null;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
//            em.createQuery("DELETE FROM OrderItem oi WHERE oi.order.id = :orderId")
//                    .setParameter("orderId", c.getId())
//                    .executeUpdate();
//            c.setOrderItems(null);
            em.remove(em.merge(c));
            tx.commit();
            result = Either.right(0);
        } catch (Exception ex) {
            result = Either.left(new RestaurantError(Constants.ERROR_DELETING_ORDER +"\n"+ex.getMessage()));
        } finally {
            if (em != null) em.close();
        }
        return result;
    }
}
