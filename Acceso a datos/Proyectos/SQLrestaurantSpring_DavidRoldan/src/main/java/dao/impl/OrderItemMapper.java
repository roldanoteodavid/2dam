package dao.impl;

import common.Constants;
import jakarta.inject.Inject;
import model.MenuItem;
import model.OrderItem;
import org.springframework.jdbc.core.RowMapper;
import services.MenuItemService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemMapper implements RowMapper<OrderItem> {

    @Override
    public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        int orderItemId = rs.getInt(Constants.ORDER_ITEM_ID);
        int orderId = rs.getInt(Constants.ORDER_ID);
        int menuItemId = rs.getInt(Constants.MENU_ITEM_ID);
        int quantity = rs.getInt(Constants.QUANTITY);
        String name = rs.getString(Constants.NAME);
        String description = rs.getString(Constants.DESCRIPTION);
        double price = rs.getDouble(Constants.PRICE);
        return new OrderItem(orderItemId, orderId, new MenuItem(menuItemId, name, description, price), quantity);
    }




}
