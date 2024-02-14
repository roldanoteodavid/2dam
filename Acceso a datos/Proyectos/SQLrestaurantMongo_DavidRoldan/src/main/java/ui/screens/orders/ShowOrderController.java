package ui.screens.orders;

import common.Constants;
import jakarta.inject.Inject;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Order;
import model.OrderItem;
import org.bson.types.ObjectId;
import services.CustomerService;
import services.MenuItemService;
import services.OrderItemService;
import services.OrderService;
import ui.screens.common.BaseScreenController;

import java.io.IOException;
import java.security.Timestamp;
import java.util.List;

public class ShowOrderController extends BaseScreenController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final OrderItemService orderItemService;
    private final MenuItemService menuItemService;
    @FXML
    private TableView<Order> ordersTable;
    @FXML
    private TableColumn<Timestamp, Order> dateOrderColumn;
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
    private ComboBox<ObjectId> customerComboBox;
    @FXML
    private TableView<OrderItem> itemsTable;
    @FXML
    private TableColumn<OrderItem, String> menuItemItemColumn;
    @FXML
    private TableColumn<Integer, OrderItem> quantityItemColumn;
    @FXML
    private Label priceLabel;

    @Inject
    public ShowOrderController(OrderService orderService, CustomerService customerService, OrderItemService orderItemService, MenuItemService menuItemService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.orderItemService = orderItemService;
        this.menuItemService = menuItemService;
    }

    public void initialize() {
        dateOrderColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.DATE));
        tableOrderColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.TABLE_ID));

        ordersTable.setOnMouseClicked(this::handleTableClick);
        dateField.setVisible(false);
        customerComboBox.setVisible(false);
        customerService.getAll().peek(customers -> customers.forEach(customer -> customerComboBox.getItems().add(customer.getId())));
        filterButton.setVisible(false);
        filterComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (Constants.DATE.equals(newValue)) {
                dateField.setVisible(true);
                customerComboBox.setVisible(false);
                filterButton.setVisible(true);
            } else if (Constants.CUSTOMER.equals(newValue)) {
                dateField.setVisible(false);
                customerComboBox.setVisible(true);
                filterButton.setVisible(true);
            } else if (Constants.NONE.equals(newValue)) {
                dateField.setVisible(false);
                customerComboBox.setVisible(false);
                filterButton.setVisible(false);
                setTables();
            }
        });

        menuItemItemColumn.setCellValueFactory(cellData -> new SimpleStringProperty(menuItemService.get(cellData.getValue().getMenu_item_id()).get().getName()));
        quantityItemColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.QUANTITY));
    }

    @Override
    public void principalCargado() throws IOException {
        if (!getPrincipalController().actualCredential.getUser_name().equals("root")) {
            filterComboBox.getItems().addAll(Constants.DATE, Constants.NONE);
            customernameLabel.setText(customerService.get(getPrincipalController().actualCredential.getId()).get().getFirst_name());
        } else {
            filterComboBox.getItems().addAll(Constants.DATE, Constants.CUSTOMER, Constants.NONE);
        }
        setTables();
    }

    private void handleTableClick(MouseEvent event) {
        if (event.getClickCount() == 1) {
            Order selectedOrder = ordersTable.getSelectionModel().getSelectedItem();
            if (selectedOrder != null) {
                clearAll();
                customernameLabel.setText(customerService.get(selectedOrder.getOrder_date()).get().getFirst_name());
                itemsTable.getItems().clear();
                List<OrderItem> orderItemList = selectedOrder.getOrderItems();
                if (orderItemList!=null) {
                    itemsTable.getItems().addAll(orderItemList);
                    priceLabel.setText(orderItemService.getTotalPrice(orderItemList) + Constants.EURO);
                }
            }
        }
    }

    private void setTables() {
        ordersTable.getItems().clear();
        if (!getPrincipalController().actualCredential.getUser_name().equals("root")) {
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
            if (!getPrincipalController().actualCredential.getUser_name().equals("root")) {
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
