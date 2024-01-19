package ui.screens.orders;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Order;
import model.OrderItem;
import services.CustomerService;
import services.OrderItemService;
import services.OrderService;
import ui.screens.common.BaseScreenController;

import java.io.IOException;
import java.security.Timestamp;

public class ShowOrderController extends BaseScreenController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final OrderItemService orderItemService;
    public TableView<Order> ordersTable;
    public TableColumn<Integer, Order> idOrderColumn;
    public TableColumn<Timestamp, Order> dateOrderColumn;
    public TableColumn<Integer, Order> customerOrderColumn;
    public TableColumn<Integer, Order> tableOrderColumn;
    @FXML
    public ComboBox filterComboBox;
    @FXML
    public DatePicker dateField;
    @FXML
    public TextField customerOrderField;
    @FXML
    public Label customernameLabel;
    public Button filterButton;
    public ComboBox<Integer> customerComboBox;
    public TableView<OrderItem> itemsTable;
    public TableColumn<Integer, OrderItem> idItemColumn;
    public TableColumn<String, OrderItem> menuItemItemColumn;
    public TableColumn<Integer, OrderItem> quantityItemColumn;

    @Inject
    public ShowOrderController(OrderService orderService, CustomerService customerService, OrderItemService orderItemService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.orderItemService = orderItemService;
    }

    public void initialize() throws IOException {
        idOrderColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateOrderColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        customerOrderColumn.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        tableOrderColumn.setCellValueFactory(new PropertyValueFactory<>("table_id"));
        ordersTable.setOnMouseClicked(this::handleTableClick);
        filterComboBox.getItems().addAll("Date", "Customer", "None");
        dateField.setVisible(false);
        customerComboBox.setVisible(false);
        customerService.getAll().peek(customers -> customers.forEach(customer -> customerComboBox.getItems().add(customer.getId())));
        filterButton.setVisible(false);
        filterComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if ("Date".equals(newValue)) {
                dateField.setVisible(true);
                customerComboBox.setVisible(false);
                filterButton.setVisible(true);
            } else if ("Customer".equals(newValue)) {
                dateField.setVisible(false);
                customerComboBox.setVisible(true);
                filterButton.setVisible(true);
            } else if ("None".equals(newValue)) {
                dateField.setVisible(false);
                customerComboBox.setVisible(false);
                filterButton.setVisible(false);
                setTables();
            }
        });

        idItemColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        menuItemItemColumn.setCellValueFactory(new PropertyValueFactory<>("menuItem"));
        quantityItemColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    @Override
    public void principalCargado() throws IOException {
        setTables();
    }

    private void handleTableClick(MouseEvent event) {
        if (event.getClickCount() == 1) {
            Order selectedOrder = ordersTable.getSelectionModel().getSelectedItem();
            if (selectedOrder != null) {
                customernameLabel.setText(String.valueOf(selectedOrder.getCustomer_id()));
                customernameLabel.setText(customerService.get(selectedOrder.getCustomer_id()).get().getFirst_name());
                itemsTable.getItems().clear();
                itemsTable.getItems().addAll(orderItemService.orderItemsOrderdByQuantity(selectedOrder));
            }
        }
    }

    private void setTables() {
        ordersTable.getItems().clear();
        orderService.getAll().peek(orders -> ordersTable.getItems().addAll(orders))
                .peekLeft(orderError -> getPrincipalController().sacarAlertError(orderError.getMessage()));
    }

    public void filterOrders() {
        if (dateField.isVisible()) {
            if (dateField != null) {
                ordersTable.getItems().clear();
                ordersTable.getItems().addAll(orderService.getOrdersByDate(dateField.getValue()));
            }
        } else if (customerComboBox.isVisible()) {
            if (customerComboBox.getSelectionModel().getSelectedItem() != null) {
                ordersTable.getItems().clear();
                ordersTable.getItems().addAll(orderService.getOrdersByCustomerId((Integer) customerComboBox.getSelectionModel().getSelectedItem()));
            }
        }
    }
}
