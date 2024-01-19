package dao;

import io.vavr.control.Either;
import model.MenuItem;
import model.errors.RestaurantError;

import java.util.List;

public interface MenuItemDAO {

    Either<RestaurantError, List<MenuItem>> getAll();
}
