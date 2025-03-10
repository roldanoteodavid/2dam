package ui.screens.orders;

import common.Constants;
import jakarta.inject.Inject;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Order;
import model.OrderItem;
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
    public TableView<Order> ordersTable;
    public TableColumn<Integer, Order> idOrderColumn;
    public TableColumn<Timestamp, Order> dateOrderColumn;
    public TableColumn<Integer, Order> customerOrderColumn;
    public TableColumn<Integer, Order> tableOrderColumn;
    public TableView<OrderItem> itemsTable;
    public TableColumn<Integer, OrderItem> idItemColumn;
    public TableColumn<String, OrderItem> menuItemItemColumn;
    public TableColumn<Integer, OrderItem> quantityItemColumn;
    private Order selectedOrder;


    @Inject
    public DeleteOrderController(OrderService orderService, OrderItemService orderItemService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
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
    }

    private void handleTableClick(MouseEvent event) {
        if (event.getClickCount() == 1) {
            Order selectedOrderEx = ordersTable.getSelectionModel().getSelectedItem();
            if (selectedOrderEx != null) {
                this.selectedOrder = selectedOrderEx;
                itemsTable.getItems().clear();
                orderItemService.getAll(selectedOrder.getId()).peek(orderItems -> itemsTable.getItems().addAll(orderItems))
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

    public void deleteOrder() {
        if (selectedOrder != null) {
            List<OrderItem> orderItemList = new ArrayList<>();
            orderItemService.getAll(selectedOrder.getId()).peek(orderItemList::addAll);
            //if (orderItemService.getAll(selectedOrder.getId()).isLeft() || orderItemService.getAll(selectedOrder.getId()).peek()) {
            if (orderItemList.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.getButtonTypes().remove(ButtonType.OK);
                alert.getButtonTypes().add(ButtonType.CANCEL);
                alert.getButtonTypes().add(ButtonType.YES);
                alert.setTitle(Constants.DELETE_ORDER);
                alert.setContentText(Constants.DO_YOU_WANT_TO_DELETE_THE_SELECTED_ORDER);
                Optional<ButtonType> res = alert.showAndWait();
                res.ifPresent(buttonType -> {
                    if (buttonType == ButtonType.YES) {
                        orderItemService.delete(selectedOrder);
                        orderService.delete(selectedOrder).peek(orderInt -> {
                                    if (orderInt == 0) {
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
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.getButtonTypes().remove(ButtonType.OK);
                alert.getButtonTypes().add(ButtonType.CANCEL);
                alert.getButtonTypes().add(ButtonType.YES);
                alert.setTitle(Constants.DELETE_ORDER_AND_ORDERITEMS);
                alert.setContentText(Constants.THE_SELECTED_ORDER_HAS_ORDERITEMS_DO_YOU_WANT_TO_DELETE_THE_ORDER_AND_ITS_ORDERITEMS);
                Optional<ButtonType> res = alert.showAndWait();
                res.ifPresent(buttonType -> {
                    if (buttonType == ButtonType.YES) {
                        orderItemService.delete(selectedOrder);
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
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle(Constants.SELECT_AN_ORDER);
            a.setHeaderText(null);
            a.setContentText(Constants.TO_DELETE_AN_ORDER_SELECT_IT_FIRST);
            a.show();
        }
    }
}
