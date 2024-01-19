package dao.impl;

import common.Constants;
import common.SQLQueries;
import dao.MenuItemDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.MenuItem;
import model.errors.RestaurantError;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDaoJDBC implements MenuItemDAO {

    private DBConnectionPool db;

    @Inject
    public MenuItemDaoJDBC(DBConnectionPool db) {
        this.db = db;
    }

    @Override
    public Either<RestaurantError, List<MenuItem>> getAll() {
        Either<RestaurantError, List<MenuItem>> result = null;
        try (Connection con = db.getConnection();
             Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = stmt.executeQuery(SQLQueries.SELECT_FROM_MENU_ITEMS);
            List<MenuItem> menuItems = readRS(rs);
            if (menuItems.isEmpty()) {
                result = Either.left(new RestaurantError(Constants.NO_MENU_ITEMS));
            } else {
                result = Either.right(menuItems);
            }
        } catch (SQLException ex) {
            result = Either.left(new RestaurantError(Constants.ERROR_CONNECTING_DATABASE));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, MenuItem> get(int id) {
        Either<RestaurantError, MenuItem> result = null;
        try (Connection con = db.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.SELECT_FROM_MENU_ITEMS_WHERE_MENU_ITEM_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            result = Either.right(readRS(rs).get(0));

        } catch (SQLException ex) {
            result = Either.left(new RestaurantError(Constants.ERROR_CONNECTING_DATABASE));
        }
        return result;
    }

    private List<MenuItem> readRS(ResultSet rs) {
        List<MenuItem> menuItems = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt(Constants.MENU_ITEM_ID);
                String name = rs.getString(Constants.NAME);
                String description = rs.getString(Constants.DESCRIPTION);
                int price = rs.getInt(Constants.PRICE);
                menuItems.add(new MenuItem(id, name, description, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItems;
    }
}
