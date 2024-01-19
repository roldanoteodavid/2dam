package dao.impl;

import common.Constants;
import common.SQLQueries;
import dao.TablesDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Table;
import model.errors.RestaurantError;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TablesDaoJDBC implements TablesDAO {

    private DBConnectionPool db;

    @Inject
    public TablesDaoJDBC(DBConnectionPool db) {
        this.db = db;
    }

    @Override
    public Either<RestaurantError, List<Table>> getAll() {
        Either<RestaurantError, List<Table>> result = null;
        try (Connection con = db.getConnection();
             Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = stmt.executeQuery(SQLQueries.SELECT_FROM_RESTAURANT_TABLES);
            List<Table> tables = readRS(rs);
            if (tables.isEmpty()) {
                result = Either.left(new RestaurantError(Constants.NO_TABLES));
            } else {
                result = Either.right(tables);
            }
        } catch (SQLException ex) {
            result = Either.left(new RestaurantError(Constants.ERROR_CONNECTING_DATABASE));
        }
        return result;
    }

    private List<Table> readRS(ResultSet rs) {
        List<Table> tables = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt("table_number_id");
                int numberofseats = rs.getInt("number_of_seats");
                tables.add(new Table(id, numberofseats));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tables;
    }
}
