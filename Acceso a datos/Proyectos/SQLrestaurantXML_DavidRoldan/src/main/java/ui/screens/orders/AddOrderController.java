package ui.screens.orders;

import common.Constants;
import jakarta.inject.Inject;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.MenuItem;
import model.Order;
import model.OrderItem;
import model.xml.OrderItemXML;
import model.xml.OrderXML;
import services.CustomerService;
import services.OrderItemService;
import services.OrderService;
import ui.screens.common.BaseScreenController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AddOrderController extends BaseScreenController {
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final CustomerService customerService;
    public TableView<OrderItem> itemsTable;
    public TableColumn<Integer, OrderItem> idItemColumn;
    public TableColumn<String, OrderItem> menuItemItemColumn;
    public TableColumn<Integer, OrderItem> quantityItemColumn;
    public TextField tableOrderField;
    public TextField customerOrderField;
    public TextField menuItemField;
    public TextField quantityItemField;
    public ComboBox<String> itemsComboBox;
    public ComboBox<Integer> customerComboBox;
    public TextField idOrderField;
    private List<OrderItem> orderItemList;

    @Inject
    public AddOrderController(OrderService orderService, CustomerService customerService, OrderItemService orderItemService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.orderItemService = orderItemService;
    }

    public void initialize() throws IOException {
        idItemColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        menuItemItemColumn.setCellValueFactory(new PropertyValueFactory<>("menuItem"));
        quantityItemColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        customerService.getAll().peek(customers -> customers.forEach(customer -> customerComboBox.getItems().add(customer.getId())));
        itemsComboBox.getItems().addAll("Caesar Salad", "Pasta", "Salmon", "Apple cake", "Hamburger", "Coffee");
        orderItemList = new ArrayList<>();
    }

    @Override
    public void principalCargado() throws IOException {
        setTable();
    }

    private void setTable() {
        itemsTable.getItems().clear();
        itemsTable.getItems().addAll(orderItemList);
    }

    private void clearAll(){
        itemsTable.getItems().clear();
        orderItemList.clear();
        tableOrderField.clear();
        customerComboBox.getSelectionModel().clearSelection();
        itemsComboBox.getSelectionModel().clearSelection();
        quantityItemField.clear();
    }

    public void addOrder() {
        if (tableOrderField.getText().isEmpty() || customerComboBox.getSelectionModel().getSelectedItem() == null || orderItemList.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(Constants.THERE_IS_AN_EMPTY_FIELD);
            a.show();
        } else {
            Order order = new Order(orderService.newId(), LocalDateTime.now(), customerComboBox.getSelectionModel().getSelectedItem(), Integer.parseInt(tableOrderField.getText()));
            orderService.save(order).peek(orderInt -> {
                        if (orderInt == 0) {
                            Alert a = new Alert(Alert.AlertType.INFORMATION);
                            a.setTitle(Constants.ORDER_ADDED);
                            a.setContentText(Constants.ORDER_ADDED_SUCCESSFULLY);
                            a.setHeaderText(null);
                            a.show();
                            orderItemService.save(orderItemList, order);
                            clearAll();
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
            MenuItem menuItem = new MenuItem();
            menuItem.setName((String) itemsComboBox.getSelectionModel().getSelectedItem());
            orderItem.setMenuItem(menuItem);

            if (orderItemList.add(orderItem)) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle(Constants.ITEM_ADDED);
                a.setContentText(Constants.ITEM_ADDED_SUCCESSFULLY);
                a.setHeaderText(null);
                a.show();
                setTable();
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
                setTable();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(Constants.SELECT_AN_ITEM_FOR_DELETING_IT);
            a.show();
        }
    }
}
