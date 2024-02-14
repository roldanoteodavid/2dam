package services.impl;

import dao.MenuItemDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.MenuItem;
import model.errors.RestaurantError;
import services.MenuItemService;

import java.util.List;

public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemDAO dao;
    @Inject
    public MenuItemServiceImpl(MenuItemDAO dao) {
        this.dao = dao;
    }

    public Either<RestaurantError, List<MenuItem>> getAll() {
        return dao.getAll();
    }

    @Override
    public Either<RestaurantError, MenuItem> get(int id) {
        return dao.get(id);
    }
}
