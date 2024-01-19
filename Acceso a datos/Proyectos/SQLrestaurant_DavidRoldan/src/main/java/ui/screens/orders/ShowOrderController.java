package ui.screens.orders;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Customer;
import model.Order;
import services.CustomerService;
import services.OrderService;
import ui.screens.common.BaseScreenController;

import java.io.IOException;
import java.security.Timestamp;

public class ShowOrderController extends BaseScreenController {

    public TableView<Order> ordersTable;
    public TableColumn<Integer, Order> idOrderColumn;
    public TableColumn<Timestamp, Order> dateOrderColumn;
    public TableColumn<Integer, Order> customerOrderColumn;
    public TableColumn<Integer, Order> tableOrderColumn;
    private final OrderService orderService;
    private final CustomerService customerService;
    @FXML
    public ComboBox filterComboBox;
    @FXML
    public DatePicker dateField;
    @FXML
    public TextField customerOrderField;
    @FXML
    public Label customernameLabel;
    public Button filterButton;

    @Inject
    public ShowOrderController(OrderService orderService, CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    public void initialize() throws IOException {
        idOrderColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateOrderColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        customerOrderColumn.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        tableOrderColumn.setCellValueFactory(new PropertyValueFactory<>("table_id"));
        ordersTable.setOnMouseClicked(this::handleTableClick);
        filterComboBox.getItems().addAll("Date", "Customer");
        dateField.setVisible(false);
        customerOrderField.setVisible(false);
        filterButton.setVisible(false);
        filterComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if ("Date".equals(newValue)) {
                dateField.setVisible(true);
                customerOrderField.setVisible(false);
                filterButton.setVisible(true);
            } else if ("Customer".equals(newValue)) {
                dateField.setVisible(false);
                customerOrderField.setVisible(true);
                filterButton.setVisible(true);
            }
        });
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
            }
        }
    }

    private void setTables() {
        ordersTable.getItems().clear();
        orderService.getAll().peek(orders -> ordersTable.getItems().addAll(orders))
                .peekLeft(orderError -> getPrincipalController().sacarAlertError(orderError.getMessage()));
    }

    public void filterOrders(ActionEvent actionEvent) {
        if (dateField.isVisible()){
            if (dateField!=null){
                ordersTable.getItems().clear();
                ordersTable.getItems().addAll(orderService.getOrdersByDate(dateField.getValue()));
            }
        } else if (customerOrderField.isVisible()) {
            if (customerOrderField!=null){
                ordersTable.getItems().clear();
                ordersTable.getItems().addAll(orderService.getOrdersByCustomerId(Integer.parseInt(customerOrderField.getText())));
            }
        }
    }
}
