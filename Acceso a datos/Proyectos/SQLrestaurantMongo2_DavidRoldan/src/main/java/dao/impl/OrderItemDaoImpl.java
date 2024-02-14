package dao.impl;

import common.Constants;
import dao.OrderItemDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Order;
import model.OrderItem;
import model.errors.RestaurantError;

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
        return result;
    }


    @Override
    public Either<RestaurantError, Integer> delete(Order o) {
        Either<RestaurantError, Integer> result = null;
        return result;
    }
}
