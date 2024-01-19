package services;

import io.vavr.control.Either;
import model.MenuItem;
import model.errors.RestaurantError;

import java.util.List;

public interface MenuItemService {
    Either<RestaurantError, List<MenuItem>> getAll();
    Either<RestaurantError, MenuItem> get(int id);
}
