package dao.impl;

import common.Constants;
import dao.TablesDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import model.Table;
import model.errors.RestaurantError;

import java.util.List;

public class TablesDaoImpl implements TablesDAO {

    private JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public TablesDaoImpl(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    @Override
    public Either<RestaurantError, List<Table>> getAll() {Either<RestaurantError, List<Table>> result = null;
        em = jpaUtil.getEntityManager();
        try {
            List<Table> tables = em.createQuery("from Table", Table.class).getResultList();
            result = Either.right(tables);
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR_CONNECTING_DATABASE));
        } finally {
            if (em != null) em.close();
        }
        return result;
    }
}
