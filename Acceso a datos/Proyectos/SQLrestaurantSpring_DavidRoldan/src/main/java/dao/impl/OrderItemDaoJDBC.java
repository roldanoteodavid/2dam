package dao.impl;

import common.Constants;
import common.SQLQueries;
import dao.OrderItemDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.MenuItem;
import model.Order;
import model.OrderItem;
import model.errors.RestaurantError;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDaoJDBC implements OrderItemDAO {

    private DBConnectionPool db;

    @Inject
    public OrderItemDaoJDBC(DBConnectionPool db) {
        this.db = db;
    }

    @Override
    public Either<RestaurantError, List<OrderItem>> getAll(Order o) {
        Either<RestaurantError, List<OrderItem>> result = null;
        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
        List<OrderItem> orderItems = jtm.query(SQLQueries.SELECT_FROM_ORDER_ITEMS_WHERE_ORDER_ID, new OrderItemMapper(), o.getId());
        if (orderItems.isEmpty()) {
            result = Either.left(new RestaurantError(Constants.NO_ORDER_ITEMS_FOR_THIS_ORDER));
        } else {
            result = Either.right(orderItems);
        }
        return result;
    }

    @Override
    public Either<RestaurantError, OrderItem> get(OrderItem o) {
        Either<RestaurantError, OrderItem> result = null;
        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
        //TODO BeanProoerty(Orders.class)
        List<OrderItem> orderItems = jtm.query(SQLQueries.SELECT_FROM_ORDER_ITEMS_WHERE_ORDER_ID, new OrderItemMapper(), o.getId());
        if (orderItems.isEmpty()) {
            result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_ORDER_ITEMS));
        } else {
            result = Either.right(orderItems.get(0));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, Integer> save(List<OrderItem> c) {
        Either<RestaurantError, Integer> result = null;
        try (Connection con = db.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.INSERT_INTO_ORDER_ITEMS_ORDER_ID_MENU_ITEM_ID_QUANTITY_VALUES)) {
            int rs = 0;
            for (OrderItem orderItem : c) {
                preparedStatement.setInt(1, orderItem.getIdOrder());
                preparedStatement.setInt(2, orderItem.getMenuItem().getId());
                preparedStatement.setInt(3, orderItem.getQuantity());
                rs = rs + preparedStatement.executeUpdate();
            }
            if (rs == 0) {
                result = Either.left(new RestaurantError(Constants.ERROR_SAVING_ORDER_ITEMS));
            } else {
                result = Either.right(0);
            }
        } catch (SQLException ex) {
            result = Either.left(new RestaurantError(Constants.ERROR_CONNECTING_DATABASE));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, Integer> update(List<OrderItem> c) {
        Either<RestaurantError, Integer> result = null;
        Connection con = null;
        try {
            con = db.getConnection();
            con.setAutoCommit(false);

            try (PreparedStatement preparedStatementDelete = con.prepareStatement(SQLQueries.DELETE_FROM_ORDER_ITEMS_WHERE_ORDER_ID);
                 PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.INSERT_INTO_ORDER_ITEMS_ORDER_ID_MENU_ITEM_ID_QUANTITY_VALUES)) {
                preparedStatementDelete.setInt(1, c.get(0).getIdOrder());
                preparedStatementDelete.executeUpdate();
                for (OrderItem orderItem : c) {
                    preparedStatement.setInt(1, orderItem.getIdOrder());
                    preparedStatement.setInt(2, orderItem.getMenuItem().getId());
                    preparedStatement.setInt(3, orderItem.getQuantity());
                    preparedStatement.executeUpdate();
                }
                con.commit();
                result = Either.right(0);
            } catch (SQLException ex) {
                con.rollback();
                result = Either.left(new RestaurantError(Constants.ERROR_UPDATING_ORDER_ITEMS));
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

    @Override
    public Either<RestaurantError, Integer> delete(Order o) {
        Either<RestaurantError, Integer> result = null;
        try (Connection con = db.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.DELETE_FROM_ORDER_ITEMS_WHERE_ORDER_ID)) {
            preparedStatement.setInt(1, o.getId());
            preparedStatement.executeUpdate();
            result = Either.right(1);
        } catch (SQLException ex) {
            result = Either.left(new RestaurantError(Constants.ERROR_DELETING_ORDER_ITEMS));
        }
        return result;
    }

    private OrderItem mapOrderItemFromResultSet(ResultSet rs) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(rs.getInt(Constants.ORDER_ITEM_ID));
        orderItem.setIdOrder(rs.getInt(Constants.ORDER_ID));
        orderItem.setMenuItem(getMenuItem(rs.getInt(Constants.MENU_ITEM_ID)));
        orderItem.setQuantity(rs.getInt(Constants.QUANTITY));
        return orderItem;
    }


    private MenuItem getMenuItem(int id) {
        MenuItem result = null;
        try (Connection con = db.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.SELECT_FROM_MENU_ITEMS_WHERE_MENU_ITEM_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            result = readRSMenuItem(rs);

        } catch (SQLException ex) {
            result = null;
        }
        return result;
    }

    private MenuItem readRSMenuItem(ResultSet rs) throws SQLException {
        MenuItem result = new MenuItem();
        while (rs.next()) {
            result.setId(rs.getInt(Constants.MENU_ITEM_ID));
            result.setName(rs.getString(Constants.NAME));
            result.setDescription(rs.getString(Constants.DESCRIPTION));
            result.setPrice(rs.getInt(Constants.PRICE));
        }
        return result;
    }
}
