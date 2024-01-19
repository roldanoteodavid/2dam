package ui.screens.orders;

import common.Constants;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.MenuItem;
import model.Order;
import model.OrderItem;
import services.CustomerService;
import services.OrderItemService;
import services.OrderService;
import ui.screens.common.BaseScreenController;

import java.io.IOException;
import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UpdateOrderController extends BaseScreenController {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final OrderItemService orderItemService;
    public TableView<Order> ordersTable;
    public TableColumn<Integer, Order> idOrderColumn;
    public TableColumn<Timestamp, Order> dateOrderColumn;
    public TableColumn<Integer, Order> customerOrderColumn;
    public TableColumn<Integer, Order> tableOrderColumn;
    public TableView<OrderItem> itemsTable;
    public TableColumn<Integer, OrderItem> idItemColumn;
    public TableColumn<String, OrderItem> nameItemColumn;
    public DatePicker dateField;
    public TextField tableOrderField;
    public TextField customerOrderField;
    public TextField menuItemField;
    public TextField quantityItemField;
    public ComboBox<String> itemsComboBox;
    public TableColumn<String, OrderItem> menuItemItemColumn;
    public TableColumn<Integer, OrderItem> quantityItemColumn;
    public ComboBox<Integer> customerComboBox;
    private Order selectedOrder;
    private List<OrderItem> orderItemList;


    @Inject
    public UpdateOrderController(OrderService orderService, OrderItemService orderItemService, CustomerService customerService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.customerService = customerService;
    }

    public void initialize() throws IOException {
        idOrderColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateOrderColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        customerOrderColumn.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        tableOrderColumn.setCellValueFactory(new PropertyValueFactory<>("table_id"));

        idItemColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        menuItemItemColumn.setCellValueFactory(new PropertyValueFactory<>("menuItem"));
        quantityItemColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        ordersTable.setOnMouseClicked(this::handleTableClick);
        customerService.getAll().peek(customers -> customers.forEach(customer -> customerComboBox.getItems().add(customer.getId())));
        itemsComboBox.getItems().addAll("Caesar Salad", "Pasta", "Salmon", "Apple cake", "Hamburger", "Coffee");
        dateField.setDisable(true);
        orderItemList = new ArrayList<>();
    }

    private void handleTableClick(MouseEvent event) {
        if (event.getClickCount() == 1) {
            clearAll();
            Order selectedOrderEx = ordersTable.getSelectionModel().getSelectedItem();
            if (selectedOrderEx != null) {
                this.selectedOrder = selectedOrderEx;
                tableOrderField.setText(String.valueOf(selectedOrder.getTable_id()));
                customerComboBox.setValue(selectedOrder.getCustomer_id());
                dateField.setValue(LocalDate.from(selectedOrder.getDate()));
                itemsTable.getItems().clear();
                orderItemService.getAll(selectedOrder.getId()).peek(orderItems -> itemsTable.getItems().addAll(orderItems))
                        .peekLeft(orderError -> getPrincipalController().sacarAlertError(orderError.getMessage()));
                orderItemService.getAll(selectedOrder.getId()).peek(orderItems -> orderItemList.addAll(orderItems))
                        .peekLeft(orderError -> getPrincipalController().sacarAlertError(orderError.getMessage()));
            }
        }
    }

    @Override
    public void principalCargado() throws IOException {
        setTables();
    }

    private void setTables() {
        ordersTable.getItems().clear();
        orderService.getAll().peek(orders -> ordersTable.getItems().addAll(orders))
                .peekLeft(orderError -> getPrincipalController().sacarAlertError(orderError.getMessage()));
    }

    private void clearAll() {
        itemsTable.getItems().clear();
        orderItemList.clear();
        tableOrderField.clear();
        customerComboBox.getSelectionModel().clearSelection();
        itemsComboBox.getSelectionModel().clearSelection();
        quantityItemField.clear();
    }

    public void updateOrder() {
        if (tableOrderField.getText().isEmpty() || customerComboBox.getSelectionModel().getSelectedItem() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(Constants.THERE_IS_AN_EMPTY_FIELD);
            a.show();
        } else {
            Order updatedorder = new Order(selectedOrder.getId(), LocalDateTime.now(), customerComboBox.getSelectionModel().getSelectedItem(), Integer.parseInt(tableOrderField.getText()));
            orderService.update(updatedorder).peek(orderInt -> {
                        if (orderInt == 0) {
                            Alert a = new Alert(Alert.AlertType.INFORMATION);
                            a.setTitle(Constants.ORDER_UPDATED);
                            a.setContentText(Constants.ORDER_UPDATED_SUCCESSFULLY);
                            a.setHeaderText(null);
                            a.show();
                            orderItemService.update(orderItemList, updatedorder);
                            clearAll();
                            setTables();
                        }
                    })
                    .peekLeft(orderError -> getPrincipalController().sacarAlertError(orderError.getMessage()));

        }
    }

    public void addItem() {
        if (quantityItemField.getText().isEmpty() || itemsComboBox.getSelectionModel().getSelectedItem() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(Constants.THERE_IS_AN_EMPTY_FIELD);
            a.show();
        } else {
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(Integer.parseInt(quantityItemField.getText()));
            model.MenuItem menuItem = new MenuItem();
            menuItem.setName((String) itemsComboBox.getSelectionModel().getSelectedItem());
            orderItem.setMenuItem(menuItem);

            if (orderItemList.add(orderItem)) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle(Constants.ITEM_ADDED);
                a.setContentText(Constants.ITEM_ADDED_SUCCESSFULLY);
                a.setHeaderText(null);
                a.show();
                itemsTable.getItems().clear();
                itemsTable.getItems().addAll(orderItemList);
            }
        }
    }

    public void removeItem() {
        if (itemsTable.getSelectionModel().getSelectedItem() != null) {
            if (orderItemList.remove(itemsTable.getSelectionModel().getSelectedItem())) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle(Constants.ITEM_REMOVED);
                a.setContentText(Constants.ITEM_REMOVED_SUCCESSFULLY);
                a.setHeaderText(null);
                a.show();
                itemsTable.getItems().clear();
                itemsTable.getItems().addAll(orderItemList);
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(Constants.SELECT_AN_ITEM_FOR_DELETING_IT);
            a.show();
        }
    }
}
