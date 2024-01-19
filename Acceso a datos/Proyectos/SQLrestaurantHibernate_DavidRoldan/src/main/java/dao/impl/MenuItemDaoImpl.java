package dao.impl;

import common.Constants;
import dao.MenuItemDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import model.MenuItem;
import model.errors.RestaurantError;

import java.util.List;

public class MenuItemDaoImpl implements MenuItemDAO {

    private JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public MenuItemDaoImpl(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    @Override
    public Either<RestaurantError, List<MenuItem>> getAll() {
        Either<RestaurantError, List<MenuItem>> result = null;
        em = jpaUtil.getEntityManager();
        try {
            List<MenuItem> menuItems = em.createQuery("from MenuItem", MenuItem.class).getResultList();
            result = Either.right(menuItems);
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_MENU_ITEMS));
        } finally {
            if (em != null) em.close();
        }
        return result;
    }

    @Override
    public Either<RestaurantError, MenuItem> get(int id) {
        Either<RestaurantError, MenuItem> result = null;
        em = jpaUtil.getEntityManager();
        try {
            MenuItem menuItem = em.find(MenuItem.class, id);
            result = Either.right(menuItem);
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_MENU_ITEM));
        } finally {
            if (em != null) em.close();
        }
        return result;
    }
}
