package dao.impl;

import common.Constants;
import common.SQLQueries;
import dao.OrderDAO;
import domain.modelo.Order;
import domain.modelo.errores.*;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class OrderDaoImpl implements OrderDAO {

    private final DBConnectionPool db;

    @Inject
    public OrderDaoImpl(DBConnectionPool db) {
        this.db = db;
    }

    @Override
    public Either<ApiError, List<Order>> getAll() {
        Either<ApiError, List<Order>> result = null;
        try (Connection con = db.getConnection(); Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = stmt.executeQuery(SQLQueries.SELECT_FROM_ORDERS);
            List<Order> orders = readRS(rs);
            if (orders.isEmpty()) {
                log.error(Constants.THE_ORDER_LIST_IS_EMPTY);
                throw new EmptyListException(Constants.THE_ORDER_LIST_IS_EMPTY);
            } else {
                result = Either.right(orders);
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new ErrorConnectingDatabaseException(Constants.ERROR_OBTAINING_ORDERS);
        }
        return result;
    }

    @Override
    public Either<ApiError, Order> get(int id) {
        Either<ApiError, Order> result = null;
        try (Connection con = db.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.SELECT_FROM_ORDERS_WHERE_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            List<Order> orders = readRS(rs);
            if (orders.isEmpty()) {
                log.error(Constants.ERROR_OBTAINING_ORDER + id);
                throw new NotFoundException(Constants.ORDER + " " + id + " " + Constants.NOT_FOUND);
            } else {
                result = Either.right(orders.get(0));
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new ErrorConnectingDatabaseException(Constants.ERROR_CONNECTING_DATABASE);
        }
        return result;
    }

    @Override
    public Either<ApiError, Integer> save(Order c) {
        Either<ApiError, Integer> result = null;
        try (Connection con = db.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.INSERT_INTO_ORDERS_ORDER_DATE_CUSTOMER_ID_TABLE_ID_VALUES)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(c.getDate()));
            preparedStatement.setInt(2, c.getCustomerid());
            preparedStatement.setInt(3, c.getTableid());

            int rs = preparedStatement.executeUpdate();
            if (rs == 0) {
                log.error(Constants.ERROR_SAVING_ORDER + c);
                throw new NotFoundException(Constants.ORDER + " " + c.getId() + " " + Constants.NOT_FOUND);
            } else {
                result = Either.right(0);
            }
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1452) {
                log.error(Constants.CUSTOMER + " " + c.getCustomerid() + " " + Constants.NOT_FOUND + " " + ex.getMessage());
                throw new CustomerDoesNotExistException(Constants.CUSTOMER + " " + c.getCustomerid() + " " + Constants.NOT_FOUND);
            } else {
                log.error(ex.getMessage(), ex);
                throw new ErrorConnectingDatabaseException(ex.getMessage());
            }
        }
        return result;
    }

    @Override
    public Either<ApiError, Integer> update(Order c) {
        Either<ApiError, Integer> result;
        try (Connection con = db.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.UPDATE_ORDERS_SET_ORDER_DATE_CUSTOMER_ID_TABLE_ID_WHERE_ORDER_ID)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(c.getDate()));
            preparedStatement.setInt(2, c.getCustomerid());
            preparedStatement.setInt(3, c.getTableid());
            preparedStatement.setInt(4, c.getId());

            int rs = preparedStatement.executeUpdate();
            if (rs == 0) {
                log.error(Constants.ERROR_UPDATING_ORDER + c);
                throw new NotFoundException(Constants.ORDER + " " + c.getId() + " " + Constants.NOT_FOUND);
            } else {
                result = Either.right(0);
            }
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1452) {
                log.error(Constants.CUSTOMER + " " + c.getCustomerid() + " " + Constants.NOT_FOUND + " " + ex.getMessage());
                throw new CustomerDoesNotExistException(Constants.CUSTOMER + " " + c.getCustomerid() + " " + Constants.NOT_FOUND);
            } else {
                log.error(ex.getMessage(), ex);
                throw new ErrorConnectingDatabaseException(ex.getMessage());
            }
        }
        return result;
    }


    @Override
    public Either<ApiError, Integer> delete(Order c) {
        Either<ApiError, Integer> result = null;
        try (Connection con = db.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.DELETE_FROM_ORDERS_WHERE_ORDER_ID)) {
            preparedStatement.setInt(1, c.getId());
            int rs = preparedStatement.executeUpdate();
            if (rs == 0) {
                log.error(Constants.ERROR_DELETING_ORDER + c);
                throw new NotFoundException(Constants.ORDER + " " + c.getId() + " " + Constants.NOT_FOUND);
            } else {
                result = Either.right(0);
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new ErrorConnectingDatabaseException(ex.getMessage());
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
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        }
        return orders;
    }
}
