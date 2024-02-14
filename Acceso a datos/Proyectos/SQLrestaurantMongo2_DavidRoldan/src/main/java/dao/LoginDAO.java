package dao;

import io.vavr.control.Either;
import model.Credential;
import model.errors.RestaurantError;

public interface LoginDAO {
    Either<RestaurantError, Credential> get(Credential credential);
    Either<RestaurantError, Integer> save(Credential credential);
}
