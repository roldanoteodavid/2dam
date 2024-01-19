package common;

public class SQLQueries {
    private SQLQueries() {
    }
    public static final String SELECT_FROM_CUSTOMERS_WHERE_ID = "select * from customers where id = ?";
    public static final String SELECT_FROM_CUSTOMERS = "SELECT * FROM customers";
    public static final String INSERT_INTO_CUSTOMERS_ID_FIRST_NAME_LAST_NAME_EMAIL_PHONE_DATE_OF_BIRTH_VALUES = "INSERT INTO customers (id, first_name, last_name, email, phone, date_of_birth) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_CUSTOMERS_SET_FIRST_NAME_LAST_NAME_EMAIL_PHONE_DATE_OF_BIRTH_WHERE_ID = "UPDATE customers SET first_name = ?, last_name = ?, email = ?, phone = ?, date_of_birth = ? WHERE id = ?";
    public static final String DELETE_FROM_CUSTOMERS_WHERE_ID = "DELETE FROM customers WHERE id = ?";
    public static final String DELETE_FROM_CREDENTIALS_WHERE_CUSTOMER_ID = "DELETE FROM credentials WHERE id = ?";
    public static final String DELETE_FROM_ORDER_ITEMS_WHERE_ORDER_ID_SELECT_ORDER_ID_FROM_ORDERS_WHERE_CUSTOMER_ID = "DELETE FROM order_items WHERE order_id = (SELECT order_id FROM orders WHERE customer_id = ?)";
    public static final String DELETE_FROM_ORDERS_WHERE_CUSTOMER_ID = "DELETE FROM orders WHERE customer_id = ?";
    public static final String SELECT_FROM_CREDENTIALS_WHERE_USER_NAME_AND_PASSWORD = "select * from credentials where user_name = ? and password = ?";
    public static final String SELECT_FROM_MENU_ITEMS = "SELECT * FROM menu_items";
    public static final String SELECT_FROM_RESTAURANT_TABLES = "SELECT * FROM restaurant_tables";
    public static final String SELECT_FROM_ORDERS = "SELECT * FROM orders";
    public static final String SELECT_FROM_ORDERS_WHERE_ID = "select * from orders where id = ?";
    public static final String INSERT_INTO_ORDERS_ORDER_DATE_CUSTOMER_ID_TABLE_ID_VALUES = "INSERT INTO orders (order_date, customer_id, table_id) VALUES (?, ?, ?)";
    public static final String UPDATE_ORDERS_SET_ORDER_DATE_CUSTOMER_ID_TABLE_ID_WHERE_ORDER_ID = "UPDATE orders SET order_date = ?, customer_id = ?, table_id = ? WHERE order_id = ?";
    public static final String DELETE_FROM_ORDERS_WHERE_ORDER_ID = "DELETE FROM orders WHERE order_id = ?";
    public static final String DELETE_FROM_ORDER_ITEMS_WHERE_ORDER_ID = "DELETE FROM order_items WHERE order_id = ?";
    //public static final String SELECT_FROM_ORDER_ITEMS_WHERE_ORDER_ID = "select * from order_items where order_id = ?";
    public static final String SELECT_FROM_ORDER_ITEMS_WHERE_ORDER_ID = "SELECT oi.order_item_id, oi.order_id, oi.menu_item_id, oi.quantity, mi.name, mi.description, mi.price FROM order_items oi INNER JOIN menu_items mi ON oi.menu_item_id = mi.menu_item_id WHERE oi.order_id=?";
    public static final String INSERT_INTO_ORDER_ITEMS_ORDER_ID_MENU_ITEM_ID_QUANTITY_VALUES = "INSERT INTO order_items (order_id, menu_item_id, quantity) VALUES (?, ?, ?)";
    public static final String SELECT_FROM_MENU_ITEMS_WHERE_MENU_ITEM_ID = "SELECT * FROM menu_items WHERE menu_item_id = ?";
    public static final String INSERT_INTO_CREDENTIALS_USERNAME_PASSWORD_VALUES = "INSERT INTO credentials (user_name, password) VALUES (?, ?)";
}
