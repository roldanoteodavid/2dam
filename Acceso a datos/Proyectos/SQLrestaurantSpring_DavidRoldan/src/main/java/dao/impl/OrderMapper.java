package dao.impl;

import common.Constants;
import model.Customer;
import model.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        int orderId = rs.getInt(Constants.ORDER_ID);
        LocalDateTime date = rs.getTimestamp(Constants.ORDER_DATE).toLocalDateTime();
        int customerId = rs.getInt(Constants.CUSTOMER_ID);
        int tableId = rs.getInt(Constants.TABLE_ID);
        return new Order(orderId, date, customerId, tableId);
    }
}
