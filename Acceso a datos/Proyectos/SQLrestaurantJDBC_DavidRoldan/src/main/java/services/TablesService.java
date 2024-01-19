package services;

import io.vavr.control.Either;
import model.Table;
import model.errors.RestaurantError;

import java.util.List;

public interface TablesService {
    Either<RestaurantError, List<Table>> getAll();
}
