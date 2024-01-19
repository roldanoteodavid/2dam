package ui.screens.orders;

import jakarta.inject.Inject;
import javafx.beans.property.SimpleStringProperty;
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
import java.util.ArrayList;
import java.util.List;

public class ShowOrderController extends BaseScreenController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final OrderItemService orderItemService;
    @FXML
    private TableView<Order> ordersTable;
    @FXML
    private TableColumn<Integer, Order> idOrderColumn;
    @FXML
    private TableColumn<Timestamp, Order> dateOrderColumn;
    @FXML
    private TableColumn<Integer, Order> customerOrderColumn;
    @FXML
    private TableColumn<Integer, Order> tableOrderColumn;
    @FXML
    private ComboBox<String> filterComboBox;
    @FXML
    private DatePicker dateField;
    @FXML
    private TextField customerOrderField;
    @FXML
    private Label customernameLabel;
    @FXML
    private Button filterButton;
    @FXML
    private ComboBox<Integer> customerComboBox;
    @FXML
    private TableView<OrderItem> itemsTable;
    @FXML
    private TableColumn<OrderItem, String> menuItemItemColumn;
    @FXML
    private TableColumn<Integer, OrderItem> quantityItemColumn;
    @FXML
    private Label priceLabel;

    @Inject
    public ShowOrderController(OrderService orderService, CustomerService customerService, OrderItemService orderItemService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.orderItemService = orderItemService;
    }

    public void initialize() {
        idOrderColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateOrderColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        customerOrderColumn.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        tableOrderColumn.setCellValueFactory(new PropertyValueFactory<>("table_id"));
        ordersTable.setOnMouseClicked(this::handleTableClick);
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

        menuItemItemColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMenuItem().getName()));
        quantityItemColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    @Override
    public void principalCargado() throws IOException {
        if (getPrincipalController().actualCredential.getId() > 0) {
            filterComboBox.getItems().addAll("Date", "None");
            customernameLabel.setText(customerService.get(getPrincipalController().actualCredential.getId()).get().getFirstname());
        } else {
            filterComboBox.getItems().addAll("Date", "Customer", "None");
        }
        setTables();
    }

    private void handleTableClick(MouseEvent event) {
        if (event.getClickCount() == 1) {
            Order selectedOrder = ordersTable.getSelectionModel().getSelectedItem();
            if (selectedOrder != null) {
                clearAll();
                customernameLabel.setText(customerService.get(selectedOrder.getCustomer_id()).get().getFirstname());
                itemsTable.getItems().clear();
                List<OrderItem> orderItemList = new ArrayList<>();
                orderItemService.getAll(selectedOrder).peek(orderItemList::addAll)
                        .peekLeft(orderError -> getPrincipalController().sacarAlertError(orderError.getMessage()));
                if (!orderItemList.isEmpty()) {
                    itemsTable.getItems().addAll(orderItemList);
                    priceLabel.setText(orderItemService.getTotalPrice(orderItemList) + "€");
                }
            }
        }
    }

    private void setTables() {
        ordersTable.getItems().clear();
        if (getPrincipalController().actualCredential.getId() > 0) {
            ordersTable.getItems().addAll(orderService.getOrdersByCustomerId(getPrincipalController().actualCredential.getId()));
        } else {
            orderService.getAll().peek(orders -> ordersTable.getItems().addAll(orders))
                    .peekLeft(orderError -> getPrincipalController().sacarAlertError(orderError.getMessage()));
        }
    }

    private void clearAll() {
        itemsTable.getItems().clear();
        customernameLabel.setText("");
        priceLabel.setText("");
    }

    public void filterOrders() {
        if (dateField.isVisible() && dateField.getValue() != null) {
            ordersTable.getItems().clear();
            if (getPrincipalController().actualCredential.getId() > 0) {
                ordersTable.getItems().addAll(orderService.getOrdersByDateAndCustomerId(dateField.getValue(), getPrincipalController().actualCredential.getId()));
            } else {
                ordersTable.getItems().addAll(orderService.getOrdersByDate(dateField.getValue()));
            }

        } else if (customerComboBox.isVisible() && customerComboBox.getSelectionModel().getSelectedItem() != null) {
            ordersTable.getItems().clear();
            ordersTable.getItems().addAll(orderService.getOrdersByCustomerId(customerComboBox.getSelectionModel().getSelectedItem()));
        }
    }
}
