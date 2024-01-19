package services.impl;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Credential;
import model.errors.RestaurantError;
import services.LoginService;

public class LoginServiceImpl implements LoginService {
    @Inject
    private LoginDAO dao;

    @Override
    public Either<RestaurantError, Credential> get(Credential credential) {
        return dao.get(credential);
    }
}
