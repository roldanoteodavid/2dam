package dao.impl;

import common.Constants;
import common.SQLQueries;
import dao.OrderItemDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Order;
import model.OrderItem;
import model.errors.RestaurantError;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderItemDaoImpl implements OrderItemDAO {

    private JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public OrderItemDaoImpl(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    @Override
    public Either<RestaurantError, List<OrderItem>> getAll(Order o) {
        Either<RestaurantError, List<OrderItem>> result = null;
        em = jpaUtil.getEntityManager();
        try {
            List<OrderItem> orderItems = em.createQuery("FROM OrderItem oi WHERE oi.order.id = :orderId", OrderItem.class)
                    .setParameter("orderId", o.getId())
                    .getResultList();

            if (orderItems.isEmpty()) {
                result = Either.left(new RestaurantError(Constants.NO_ORDER_ITEMS_FOR_THIS_ORDER));
            } else {
                result = Either.right(orderItems);
            }
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_ORDER_ITEMS));
        } finally {
            if (em != null) em.close();
        }
        return result;
    }

    @Override
    public Either<RestaurantError, OrderItem> get(OrderItem o) {
        Either<RestaurantError, OrderItem> result = null;
        em = jpaUtil.getEntityManager();
        try {
            OrderItem orderItem = em.find(OrderItem.class, o.getId());
            if (orderItem == null) {
                result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_ORDER_ITEMS));
            } else {
                result = Either.right(orderItem);
            }
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_ORDER_ITEMS));
        } finally {
            if (em != null) em.close();
        }
        return result;
    }

    @Override
    public Either<RestaurantError, Integer> save(List<OrderItem> c) {
        Either<RestaurantError, Integer> result = null;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            for (OrderItem orderItem : c) {
                em.persist(orderItem);
            }
            tx.commit();
            result = Either.right(0);
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            result = Either.left(new RestaurantError(Constants.ERROR_SAVING_ORDER_ITEMS));
        } finally {
            if (em != null) em.close();
        }
        return result;
    }

    @Override
    public Either<RestaurantError, Integer> update(List<OrderItem> c) {
        Either<RestaurantError, Integer> result = null;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            // Eliminar los OrderItem existentes asociados a la orden
            em.createQuery("DELETE FROM OrderItem oi WHERE oi.order.id = :orderId")
                    .setParameter("orderId", c.get(0).getOrder().getId())
                    .executeUpdate();

            // Persistir las instancias (o hacer merge antes de persistir)
            for (OrderItem orderItem : c) {
                em.persist(em.contains(orderItem) ? orderItem : em.merge(orderItem));
            }

            tx.commit();
            result = Either.right(0);
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            result = Either.left(new RestaurantError(Constants.ERROR_UPDATING_ORDER_ITEMS));
        } finally {
            if (em != null) em.close();
        }
        return result;
    }


    @Override
    public Either<RestaurantError, Integer> delete(Order o) {
        Either<RestaurantError, Integer> result = null;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.createQuery("DELETE FROM OrderItem oi WHERE oi.order.id = :orderId")
                    .setParameter("orderId", o.getId())
                    .executeUpdate();
            tx.commit();
            result = Either.right(1);
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            result = Either.left(new RestaurantError(Constants.ERROR_DELETING_ORDER_ITEMS));
        } finally {
            if (em != null) em.close();
        }
        return result;
    }
}
