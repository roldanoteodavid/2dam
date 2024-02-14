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
import org.bson.types.ObjectId;
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
    @FXML
    private TextField customernameItemField;
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
    private ComboBox<ObjectId> customerComboBox;
    @FXML
    private ComboBox<Integer> tableComboBox;
    private List<OrderItem> orderItemList;

    @Inject
    public AddOrderController(OrderService orderService, CustomerService customerService, OrderItemService orderItemService, MenuItemService menuItemService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.orderItemService = orderItemService;
        this.menuItemService = menuItemService;
    }

    public void initialize() {

        menuItemItemColumn.setCellValueFactory(cellData -> new SimpleStringProperty(menuItemService.get(cellData.getValue().getMenu_item_id()).get().getName()));
        quantityItemColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.QUANTITY));

        menuItemService.getAll().peek(menuItems -> menuItems.forEach(menuItem -> itemsComboBox.getItems().add(menuItem)));
        tableComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        orderItemList = new ArrayList<>();
    }

    @Override
    public void principalCargado() throws IOException {
        if (getPrincipalController().actualCredential.getUser_name().equals("root")){
            customernameItemField.setVisible(false);
            customerComboBox.setVisible(true);
            customerService.getAll().peek(customers -> customers.forEach(customer -> customerComboBox.getItems().add(customer.getId())));
        }else {
            customernameItemField.setText(customerService.get(getPrincipalController().actualCredential.getId()).get().getFirst_name());
            customernameItemField.setDisable(true);
            customerComboBox.setValue(getPrincipalController().actualCredential.getId());
            customerComboBox.setVisible(false);
        }
        setTable();
    }

    private void setTable() {
        itemsTable.getItems().clear();
        itemsTable.getItems().addAll(orderItemList);
    }

    private void clearAll(){
        itemsTable.getItems().clear();
        tableComboBox.getSelectionModel().clearSelection();
        customerComboBox.getSelectionModel().clearSelection();
        itemsComboBox.getSelectionModel().clearSelection();
        orderItemList.clear();
        quantityItemField.clear();
    }

    public void addOrder() {
        if (tableComboBox.getSelectionModel().getSelectedItem() == null || customerComboBox.getSelectionModel().getSelectedItem() == null || orderItemList.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(Constants.THERE_IS_AN_EMPTY_FIELD);
            a.show();
        } else {
            LocalDateTime now = LocalDateTime.now();
            Order order = new Order(now, tableComboBox.getSelectionModel().getSelectedItem(), orderItemList);
            orderService.save(order, customerComboBox.getSelectionModel().getSelectedItem()).peek(orderInt -> {
                        if (orderInt == 0) {
                            Alert a = new Alert(Alert.AlertType.INFORMATION);
                            a.setTitle(Constants.ORDER_ADDED);
                            a.setContentText(Constants.ORDER_ADDED_SUCCESSFULLY);
                            a.setHeaderText(null);
                            a.show();
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
            orderItem.setMenu_item_id(itemsComboBox.getSelectionModel().getSelectedItem().getId());

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
