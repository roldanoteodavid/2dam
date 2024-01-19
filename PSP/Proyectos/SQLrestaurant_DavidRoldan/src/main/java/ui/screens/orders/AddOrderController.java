package ui.screens.orders;

import common.Constants;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;
import model.Order;
import services.CustomerService;
import services.OrderService;
import ui.screens.common.BaseScreenController;

import java.io.IOException;
import java.time.LocalDateTime;

public class AddOrderController extends BaseScreenController {
    private final OrderService orderService;
    private final CustomerService customerService;
    public TableView<Item> itemsTable;
    public TableColumn<Integer, Item> idItemColumn;
    public TableColumn<String, Item> nameItemColumn;
    public TableColumn<Float, Item> priceItemColumn;
    public TableColumn<String, Item> descriptionItemColumn;
    public TextField tableOrderField;
    public TextField customerOrderField;
    public TextField menuItemField;
    public TextField quantityItemField;
    public ComboBox itemsComboBox;
    public TextField idOrderField;

    @Inject
    public AddOrderController(OrderService orderService, CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    public void initialize() throws IOException {
        idItemColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameItemColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceItemColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        descriptionItemColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    @Override
    public void principalCargado() throws IOException {
        setTable();
    }

    private void setTable() {
        itemsTable.getItems().clear();
    }

    public void addOrder(ActionEvent actionEvent) {
        if (tableOrderField.getText().isEmpty() || customerOrderField.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(Constants.THERE_IS_AN_EMPTY_FIELD);
            a.show();
        } else {
            if (customerService.get(Integer.parseInt(customerOrderField.getText())).isLeft()) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText(Constants.CUSTOMER_NOT_FOUND);
                a.show();
            } else {
                orderService.save(new Order(Integer.parseInt(idOrderField.getText()), LocalDateTime.now(), Integer.parseInt(customerOrderField.getText()), Integer.parseInt(tableOrderField.getText()))).peek(orderInt -> {
                            if (orderInt == 0) {
                                Alert a = new Alert(Alert.AlertType.INFORMATION);
                                a.setTitle(Constants.ORDER_ADDED);
                                a.setContentText(Constants.ORDER_ADDED_SUCCESSFULLY);
                                a.setHeaderText(null);
                                a.show();
                                setTable();
                            }
                        })
                        .peekLeft(orderError -> getPrincipalController().sacarAlertError(orderError.getMessage()));
            }

        }
    }

    public void addItem(ActionEvent actionEvent) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(Constants.ITEM_ADDED);
        a.setContentText(Constants.ITEM_ADDED_SUCCESSFULLY);
        a.setHeaderText(null);
        a.show();
    }

    public void removeItem(ActionEvent actionEvent) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(Constants.ITEM_REMOVED);
        a.setContentText(Constants.ITEM_REMOVED_SUCCESSFULLY);
        a.setHeaderText(null);
        a.show();
    }
}
