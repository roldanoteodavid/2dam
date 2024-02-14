package ui.screens.orders;

import common.Constants;
import jakarta.inject.Inject;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Order;
import model.OrderItem;
import services.MenuItemService;
import services.OrderItemService;
import services.OrderService;
import ui.screens.common.BaseScreenController;

import java.io.IOException;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeleteOrderController extends BaseScreenController {
    private final OrderService orderService;
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
    private TableColumn<OrderItem, String> menuItemItemColumn;
    @FXML
    private TableColumn<Integer, OrderItem> quantityItemColumn;
    private Order selectedOrder;


    @Inject
    public DeleteOrderController(OrderService orderService, OrderItemService orderItemService, MenuItemService menuItemService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.menuItemService = menuItemService;
    }

    public void initialize() {
        dateOrderColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.DATE));
        tableOrderColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.TABLE_ID));


        menuItemItemColumn.setCellValueFactory(cellData -> new SimpleStringProperty(menuItemService.get(cellData.getValue().getMenu_item_id()).get().getName()));
        quantityItemColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        ordersTable.setOnMouseClicked(this::handleTableClick);
    }

    private void handleTableClick(MouseEvent event) {
        if (event.getClickCount() == 1) {
            Order selectedOrderEx = ordersTable.getSelectionModel().getSelectedItem();
            if (selectedOrderEx != null) {
                this.selectedOrder = selectedOrderEx;
                itemsTable.getItems().clear();
                List<OrderItem> orderItemList = selectedOrder.getOrderItems();
                if (orderItemList!=null) {
                    itemsTable.getItems().addAll(orderItemList);
                }
            }
        }
    }

    @Override
    public void principalCargado() throws IOException {
        ordersTable.getItems().clear();
        orderService.getAll().peek(orders -> ordersTable.getItems().addAll(orders))
                .peekLeft(orderError -> getPrincipalController().sacarAlertError(orderError.getMessage()));
    }

    private void setTables() {
        ordersTable.getItems().clear();
        orderService.getAll().peek(orders -> ordersTable.getItems().addAll(orders))
                .peekLeft(orderError -> getPrincipalController().sacarAlertError(orderError.getMessage()));
        itemsTable.getItems().clear();
    }

    public void deleteOrder() {
        if (selectedOrder != null) {
            if (selectedOrder==null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.getButtonTypes().remove(ButtonType.OK);
                alert.getButtonTypes().add(ButtonType.CANCEL);
                alert.getButtonTypes().add(ButtonType.YES);
                alert.setTitle(Constants.DELETE_ORDER);
                alert.setContentText(Constants.DO_YOU_WANT_TO_DELETE_THE_SELECTED_ORDER);
                Optional<ButtonType> res = alert.showAndWait();
                deleteOrder(res);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.getButtonTypes().remove(ButtonType.OK);
                alert.getButtonTypes().add(ButtonType.CANCEL);
                alert.getButtonTypes().add(ButtonType.YES);
                alert.setTitle(Constants.DELETE_ORDER_AND_ORDERITEMS);
                alert.setContentText(Constants.THE_SELECTED_ORDER_HAS_ORDERITEMS_DO_YOU_WANT_TO_DELETE_THE_ORDER_AND_ITS_ORDERITEMS);
                Optional<ButtonType> res = alert.showAndWait();
                deleteOrder(res);
            }
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle(Constants.SELECT_AN_ORDER);
            a.setHeaderText(null);
            a.setContentText(Constants.TO_DELETE_AN_ORDER_SELECT_IT_FIRST);
            a.show();
        }
    }

    private void deleteOrder(Optional<ButtonType> res) {
        res.ifPresent(buttonType -> {
            if (buttonType == ButtonType.YES) {
                orderService.delete(selectedOrder).peek(customerInt -> {
                            if (customerInt == 0) {
                                Alert a = new Alert(Alert.AlertType.INFORMATION);
                                a.setTitle(Constants.ORDER_DELETED);
                                a.setHeaderText(null);
                                a.setContentText(Constants.THE_ORDER_HAS_BEEN_DELETED_SUCCESSFULLY);
                                a.show();
                                setTables();
                            }
                        })
                        .peekLeft(customerError -> getPrincipalController().sacarAlertError(customerError.getMessage()));
            }
        });
    }
}
