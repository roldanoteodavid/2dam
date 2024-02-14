package ui.screens.orders;

import common.Constants;
import jakarta.inject.Inject;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.MenuItem;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UpdateOrderController extends BaseScreenController {
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
    private TableView<OrderItem> itemsTable;
    @FXML
    private DatePicker dateField;
    @FXML
    private TextField quantityItemField;
    @FXML
    private ComboBox<MenuItem> itemsComboBox;
    @FXML
    private TableColumn<OrderItem, String> menuItemItemColumn;
    @FXML
    private TableColumn<Integer, OrderItem> quantityItemColumn;
    @FXML
    private ComboBox<ObjectId> customerComboBox;
    @FXML
    private ComboBox<Integer> tableComboBox;
    @FXML
    private Order selectedOrder;
    @FXML
    private List<OrderItem> orderItemList;


    @Inject
    public UpdateOrderController(OrderService orderService, OrderItemService orderItemService, CustomerService customerService, MenuItemService menuItemService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.customerService = customerService;
        this.menuItemService = menuItemService;
    }

    public void initialize() {
        dateOrderColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.DATE));
        tableOrderColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.TABLE_ID));

        menuItemItemColumn.setCellValueFactory(cellData -> new SimpleStringProperty(menuItemService.get(cellData.getValue().getMenu_item_id()).get().getName()));
        quantityItemColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.QUANTITY));
        ordersTable.setOnMouseClicked(this::handleTableClick);
        menuItemService.getAll().peek(menuItems -> menuItems.forEach(menuItem -> itemsComboBox.getItems().add(menuItem)));
        tableComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        dateField.setDisable(true);
        orderItemList = new ArrayList<>();
    }

    private void handleTableClick(MouseEvent event) {
        if (event.getClickCount() == 1) {
            clearAll();
            Order selectedOrderEx = ordersTable.getSelectionModel().getSelectedItem();
            if (selectedOrderEx != null) {
                this.selectedOrder = selectedOrderEx;
                tableComboBox.setValue(selectedOrder.getTable_id());

                customerComboBox.setValue(customerService.get(selectedOrder.getOrder_date()).get().getId());
                customerComboBox.setDisable(true);
                orderItemList = selectedOrder.getOrderItems();
                dateField.setValue(LocalDate.from(selectedOrder.getDate()));
                itemsTable.getItems().clear();
                List<OrderItem> orderItemList = selectedOrder.getOrderItems();
                if (orderItemList != null) {
                    itemsTable.getItems().addAll(orderItemList);
                }
            }
        }
    }

    @Override
    public void principalCargado() throws IOException {
        if (getPrincipalController().actualCredential.getUser_name().equals("root")) {
            customerService.getAll().peek(customers -> customers.forEach(customer -> customerComboBox.getItems().add(customer.getId())));
        } else {
            customerComboBox.setValue(getPrincipalController().actualCredential.getId());
            customerComboBox.setDisable(true);
        }
        setTables();
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
        orderItemList.clear();
        tableComboBox.getSelectionModel().clearSelection();
        customerComboBox.getSelectionModel().clearSelection();
        itemsComboBox.getSelectionModel().clearSelection();
        quantityItemField.clear();
    }

    public void updateOrder() {
        if (tableComboBox.getSelectionModel().getSelectedItem() == null || customerComboBox.getSelectionModel().getSelectedItem() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(Constants.THERE_IS_AN_EMPTY_FIELD);
            a.show();
        } else {
            //selectedOrder.getOrder_items().clear();
            //selectedOrder.setOrderItems(orderItemList);
            selectedOrder.setTable_id(tableComboBox.getSelectionModel().getSelectedItem());
//            orderItemList.forEach(orderItem -> selectedOrder.getOrderItems().add(orderItem));
//            Order updatedorder = new Order(LocalDateTime.now(), tableComboBox.getSelectionModel().getSelectedItem(), orderItemList);
            orderService.update(selectedOrder).peek(orderInt -> {
                        if (orderInt == 0) {
                            Alert a = new Alert(Alert.AlertType.INFORMATION);
                            a.setTitle(Constants.ORDER_UPDATED);
                            a.setContentText(Constants.ORDER_UPDATED_SUCCESSFULLY);
                            a.setHeaderText(null);
                            a.show();
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
            orderItem.setMenu_item_id(itemsComboBox.getSelectionModel().getSelectedItem().getId());

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
