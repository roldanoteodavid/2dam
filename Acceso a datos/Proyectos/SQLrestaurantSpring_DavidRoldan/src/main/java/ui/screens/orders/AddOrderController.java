package ui.screens.orders;

import common.Constants;
import jakarta.inject.Inject;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.MenuItem;
import model.Order;
import model.OrderItem;
import services.*;
import ui.screens.common.BaseScreenController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AddOrderController extends BaseScreenController {
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final CustomerService customerService;
    private final MenuItemService menuItemService;
    private final TablesService tablesService;
    @FXML
    private TableView<OrderItem> itemsTable;
    @FXML
    private TableColumn<OrderItem, String> menuItemItemColumn;
    @FXML
    private TableColumn<Integer, OrderItem> quantityItemColumn;
    @FXML
    private TextField quantityItemField;
    @FXML
    private ComboBox<MenuItem> itemsComboBox;
    @FXML
    private ComboBox<Integer> customerComboBox;
    @FXML
    private ComboBox<Integer> tableComboBox;
    private List<OrderItem> orderItemList;

    @Inject
    public AddOrderController(OrderService orderService, CustomerService customerService, OrderItemService orderItemService, MenuItemService menuItemService, TablesService tablesService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.orderItemService = orderItemService;
        this.menuItemService = menuItemService;
        this.tablesService = tablesService;
    }

    public void initialize() {
        menuItemItemColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMenuItem().getName()));
        quantityItemColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        menuItemService.getAll().peek(menuItems -> menuItems.forEach(menuItem -> itemsComboBox.getItems().add(menuItem)));
        tablesService.getAll().peek(tables -> tables.forEach(table -> tableComboBox.getItems().add(table.getId())));
        orderItemList = new ArrayList<>();
    }

    @Override
    public void principalCargado() throws IOException {
        if (getPrincipalController().actualCredential.getId()<0){
            customerService.getAll().peek(customers -> customers.forEach(customer -> customerComboBox.getItems().add(customer.getId())));
        }else {
            customerComboBox.setValue(getPrincipalController().actualCredential.getId());
            customerComboBox.setDisable(true);
        }
        setTable();
    }

    private void setTable() {
        itemsTable.getItems().clear();
        itemsTable.getItems().addAll(orderItemList);
    }

    private void clearAll(){
        itemsTable.getItems().clear();
        orderItemList.clear();
        tableComboBox.getSelectionModel().clearSelection();
        customerComboBox.getSelectionModel().clearSelection();
        itemsComboBox.getSelectionModel().clearSelection();
        quantityItemField.clear();
    }

    public void addOrder() {
        if (tableComboBox.getSelectionModel().getSelectedItem() == null || customerComboBox.getSelectionModel().getSelectedItem() == null || orderItemList.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(Constants.THERE_IS_AN_EMPTY_FIELD);
            a.show();
        } else {
            int idnew = orderService.newId();
            orderItemList.forEach(orderItem -> orderItem.setIdOrder(idnew));
            Order order = new Order(orderService.newId(), LocalDateTime.now(), customerComboBox.getSelectionModel().getSelectedItem(), tableComboBox.getSelectionModel().getSelectedItem());
            orderService.save(order).peek(orderInt -> {
                        if (orderInt == 0) {
                            Alert a = new Alert(Alert.AlertType.INFORMATION);
                            a.setTitle(Constants.ORDER_ADDED);
                            a.setContentText(Constants.ORDER_ADDED_SUCCESSFULLY);
                            a.setHeaderText(null);
                            a.show();
                            orderItemService.save(orderItemList);
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
            orderItem.setMenuItem(itemsComboBox.getSelectionModel().getSelectedItem());

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
