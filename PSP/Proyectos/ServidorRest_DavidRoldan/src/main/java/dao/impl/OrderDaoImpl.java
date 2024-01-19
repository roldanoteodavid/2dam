package dao.impl;

import common.Constants;
import common.SQLQueries;
import dao.OrderDAO;
import domain.modelo.Order;
import domain.modelo.errores.RestaurantError;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDAO {

    private DBConnectionPool db;

    @Inject
    public OrderDaoImpl(DBConnectionPool db) {
        this.db = db;
    }

    @Override
    public Either<RestaurantError, List<Order>> getAll() {
        Either<RestaurantError, List<Order>> result = null;
        try (Connection con = db.getConnection();
             Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = stmt.executeQuery(SQLQueries.SELECT_FROM_ORDERS);
            result = Either.right(readRS(rs));
        } catch (SQLException ex) {
            result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_ORDERS));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, Order> get(int id) {
        Either<RestaurantError, Order> result = null;
        try (Connection con = db.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.SELECT_FROM_ORDERS_WHERE_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            Order order = readRS(rs).get(0);
            if (order == null) {
                result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_ORDER));
            } else {
                result = Either.right(order);
            }
        } catch (SQLException ex) {
            result = Either.left(new RestaurantError(Constants.ERROR_CONNECTING_DATABASE));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, Integer> save(Order c) {
        Either<RestaurantError, Integer> result = null;
        try (Connection con = db.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.INSERT_INTO_ORDERS_ORDER_DATE_CUSTOMER_ID_TABLE_ID_VALUES)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(c.getDate()));
            preparedStatement.setInt(2, c.getCustomer_id());
            preparedStatement.setInt(3, c.getTable_id());

            int rs = preparedStatement.executeUpdate();
            if (rs == 0) {
                result = Either.left(new RestaurantError(Constants.ERROR_SAVING_ORDER));
            } else {
                result = Either.right(0);
            }
        } catch (SQLException ex) {
            result = Either.left(new RestaurantError(Constants.ERROR_CONNECTING_DATABASE));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, Integer> update(Order c) {
        Either<RestaurantError, Integer> result;
        try (Connection con = db.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.UPDATE_ORDERS_SET_ORDER_DATE_CUSTOMER_ID_TABLE_ID_WHERE_ORDER_ID)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(c.getDate()));
            preparedStatement.setInt(2, c.getCustomer_id());
            preparedStatement.setInt(3, c.getTable_id());
            preparedStatement.setInt(4, c.getId());

            int rs = preparedStatement.executeUpdate();
            if (rs == 0) {
                result = Either.left(new RestaurantError(Constants.ERROR_UPDATING_ORDER));
            } else {
                result = Either.right(0);
            }
        } catch (SQLException ex) {
            result = Either.left(new RestaurantError(Constants.ERROR_CONNECTING_DATABASE));
        }
        return result;
    }


    @Override
    public Either<RestaurantError, Integer> delete(Order c) {
        Either<RestaurantError, Integer> result = null;
        try (Connection con = db.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.DELETE_FROM_ORDERS_WHERE_ORDER_ID)) {
            preparedStatement.setInt(1, c.getId());
            int rs = preparedStatement.executeUpdate();
            if (rs == 0) {
                result = Either.left(new RestaurantError(Constants.ERROR_DELETING_ORDER));
            } else {
                result = Either.right(0);
            }
        } catch (SQLException ex) {
            result = Either.left(new RestaurantError(Constants.ERROR_CONNECTING_DATABASE));
        }
        return result;
    }

    private List<Order> readRS(ResultSet rs) {
        List<Order> orders = new ArrayList<>();
        try {
            while (rs.next()) {
                int orderId = rs.getInt(Constants.ORDER_ID);
                LocalDateTime date = rs.getTimestamp(Constants.ORDER_DATE).toLocalDateTime();
                int customerId = rs.getInt(Constants.CUSTOMER_ID);
                int tableId = rs.getInt(Constants.TABLE_ID);
                orders.add(new Order(orderId, date, customerId, tableId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
