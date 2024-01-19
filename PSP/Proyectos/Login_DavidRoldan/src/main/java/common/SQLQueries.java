package common;

public class SQLQueries {
    public static final String SELECT_FROM_CREDENTIALS_WHERE_USERNAME = "select * from credentials where username = ?";
    public static final String SELECT_FROM_CREDENTIALS_WHERE_ACCESSTOKEN = "select * from credentials where accesstoken = ?";
    public static final String SELECT_FROM_CREDENTIALS_WHERE_ACTIVATION_CODE = "select * from credentials where temporal = ?";
    public static final String SELECT_FROM_CUSTOMERS_WHERE_ID = "select * from customers where id = ?";
    public static final String SELECT_FROM_CUSTOMERS = "SELECT * FROM customers";
    public static final String INSERT_INTO_CUSTOMERS_ID_FIRST_NAME_LAST_NAME_EMAIL_PHONE_DATE_OF_BIRTH_VALUES = "INSERT INTO customers (id, first_name, last_name, email, phone, date_of_birth) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_CUSTOMERS_SET_FIRST_NAME_LAST_NAME_EMAIL_PHONE_DATE_OF_BIRTH_WHERE_ID = "UPDATE customers SET first_name = ?, last_name = ?, email = ?, phone = ?, date_of_birth = ? WHERE id = ?";
    public static final String UPDATE_ACCESSTOKEN_WHERE_REFRESHTOKEN = "UPDATE credentials SET accesstoken = ? WHERE refreshtoken = ?";
    public static final String UPDATE_TEMPORAL_WHERE_USERNAME = "UPDATE credentials SET temporal = ? WHERE username = ?";
    public static final String DELETE_FROM_CUSTOMERS_WHERE_ID = "DELETE FROM customers WHERE id = ?";
    public static final String SELECT_FROM_ORDERS = "SELECT * FROM orders";
    public static final String SELECT_FROM_ORDERS_WHERE_ID = "select * from orders where order_id = ?";
    public static final String INSERT_INTO_ORDERS_ORDER_DATE_CUSTOMER_ID_TABLE_ID_VALUES = "INSERT INTO orders (order_date, customer_id, table_id) VALUES (?, ?, ?)";
    public static final String UPDATE_ORDERS_SET_ORDER_DATE_CUSTOMER_ID_TABLE_ID_WHERE_ORDER_ID = "UPDATE orders SET order_date = ?, customer_id = ?, table_id = ? WHERE order_id = ?";
    public static final String DELETE_FROM_ORDERS_WHERE_ORDER_ID = "DELETE FROM orders WHERE order_id = ?";
    public static final String INSERT_INTO_CREDENTIALS = "INSERT INTO credentials (username, password, email, fecha, temporal) VALUES (?, ?, ?, ?, ?)";
    public static final String UPDATE_CREDENTIALS_SET_VALIDATE_WHERE_CODE = "UPDATE credentials SET validate = 1, temporal = NULL WHERE temporal = ?";
    public static final String UPDATE_CREDENTIALS_SET_PASSWORD_WHERE_USERNAME = "UPDATE credentials SET temporalpassword = NULL, password = ? WHERE username = ?";
    public static final String UPDATE_CREDENTIALS_SET_TEMPORAL_WHERE_USERNAME = "UPDATE credentials SET temporalpassword = ?, refreshtoken = NULL, accesstoken = NULL WHERE username = ?";
    public static final String UPDATE_VALIDATION_CODE_DATE_WHERE_CODE = "UPDATE credentials SET temporal = ?, fecha = ? WHERE temporal = ?";
    public static final String UPDATE_ACCESSTOKEN_REFRESHTOKEN_WHERE_USERNAME = "UPDATE credentials SET accesstoken = ?, refreshtoken = ?, temporal = NULL WHERE username = ?";
    private SQLQueries() {
    }
}
