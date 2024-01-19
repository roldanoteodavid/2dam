package dao.impl;

import common.Constants;
import common.SQLQueries;
import dao.OrderDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Order;
import model.errors.RestaurantError;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoJDBC implements OrderDAO {

    private DBConnectionPool db;

    @Inject
    public OrderDaoJDBC(DBConnectionPool db) {
        this.db = db;
    }

    @Override
    public Either<RestaurantError, List<Order>> getAll() {
        Either<RestaurantError, List<Order>> result = null;
        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
        List<Order> orders = jtm.query(SQLQueries.SELECT_FROM_ORDERS, new OrderMapper());

        if (orders.isEmpty()) {
            result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_ORDERS));
        } else {
            result = Either.right(orders);
        }
        return result;
    }

    @Override
    public Either<RestaurantError, Order> get(int id) {
        Either<RestaurantError, Order> result = null;
        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());

        List<Order> orders = jtm.query(SQLQueries.SELECT_FROM_ORDERS_WHERE_ID, new OrderMapper(), id);

        if (orders.isEmpty()) {
            result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_ORDER));
        } else {
            result = Either.right(orders.get(0));
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
        Connection con = null;
        try {
            con = db.getConnection();
            con.setAutoCommit(false);

            try (PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.DELETE_FROM_ORDERS_WHERE_ORDER_ID);
                 PreparedStatement preparedStatementItems = con.prepareStatement(SQLQueries.DELETE_FROM_ORDER_ITEMS_WHERE_ORDER_ID)) {
                preparedStatement.setInt(1, c.getId());
                preparedStatementItems.setInt(1, c.getId());
                preparedStatementItems.executeUpdate();
                int rs = preparedStatement.executeUpdate();

                if (rs == 0) {
                    result = Either.left(new RestaurantError(Constants.ERROR_DELETING_ORDER));
                } else {
                    result = Either.right(0);
                }
                con.commit();
            } catch (SQLException ex) {
                con.rollback();
                result = Either.left(new RestaurantError(Constants.ERROR_CONNECTING_DATABASE));
            }
        } catch (SQLException e) {
            result = Either.left(new RestaurantError(Constants.ERROR_CONNECTING_DATABASE));
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException closeEx) {
                    result = Either.left(new RestaurantError(Constants.ERROR_CONNECTING_DATABASE));
                }
            }
        }
        return result;
    }
}
