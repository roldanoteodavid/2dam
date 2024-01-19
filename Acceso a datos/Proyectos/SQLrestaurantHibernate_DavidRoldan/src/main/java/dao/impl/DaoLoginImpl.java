package dao.impl;

import common.Constants;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import model.Credential;
import model.errors.RestaurantError;

public class DaoLoginImpl implements dao.LoginDAO {

    private JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public DaoLoginImpl(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    @Override
    public Either<RestaurantError, Credential> get(Credential credential) {
        Either<RestaurantError, Credential> result = null;
        em = jpaUtil.getEntityManager();

        try {
            Credential credentialresult = em.createQuery("SELECT c FROM Credential c WHERE c.user_name = :userName AND c.password = :password", Credential.class)
                    .setParameter("userName", credential.getUser_name())
                    .setParameter("password", credential.getPassword())
                    .getSingleResult();

            result = Either.right(credentialresult);
        } catch (NoResultException e) {
            result = Either.left(new RestaurantError(Constants.CREDENTIAL_NOT_FOUND));
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_CREDENTIAL));
        } finally {
            if (em != null) em.close();
        }

        return result;
    }
}
