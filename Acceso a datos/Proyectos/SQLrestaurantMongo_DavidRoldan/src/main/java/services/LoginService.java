package services;

import io.vavr.control.Either;
import model.Credential;
import model.errors.RestaurantError;

public interface LoginService {
    Either<RestaurantError, Credential> get(Credential credential);
}
