package services.impl;

import dao.TablesDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Table;
import model.errors.RestaurantError;
import services.TablesService;

import java.util.List;

public class TablesServiceImpl implements TablesService {

    private final TablesDAO dao;
    @Inject
    public TablesServiceImpl(TablesDAO dao) {
        this.dao = dao;
    }

    public Either<RestaurantError, List<Table>> getAll() {
        return dao.getAll();
    }
}
