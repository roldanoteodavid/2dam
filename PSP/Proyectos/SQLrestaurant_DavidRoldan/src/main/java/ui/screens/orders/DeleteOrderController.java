package ui.screens.orders;

import common.Constants;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Item;
import model.Order;
import services.OrderService;
import ui.screens.common.BaseScreenController;

import java.io.IOException;
import java.security.Timestamp;

public class DeleteOrderController extends BaseScreenController {
    private final OrderService orderService;
    public TableView<Order> ordersTable;
    public TableColumn<Integer, Order> idOrderColumn;
    public TableColumn<Timestamp, Order> dateOrderColumn;
    public TableColumn<Integer, Order> customerOrderColumn;
    public TableColumn<Integer, Order> tableOrderColumn;
    public TableView<Item> itemsTable;
    public TableColumn<Integer, Item> idItemColumn;
    public TableColumn<String, Item> nameItemColumn;
    public TableColumn<Float, Item> priceItemColumn;
    public TableColumn<String, Item> descriptionItemColumn;
    private Order selectedOrder;


    @Inject
    public DeleteOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public void initialize() throws IOException {
        idOrderColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateOrderColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        customerOrderColumn.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        tableOrderColumn.setCellValueFactory(new PropertyValueFactory<>("table_id"));
        idItemColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameItemColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceItemColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        descriptionItemColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        ordersTable.setOnMouseClicked(this::handleTableClick);
    }

    private void handleTableClick(MouseEvent event) {
        if (event.getClickCount() == 1) {
            Order selectedOrder = ordersTable.getSelectionModel().getSelectedItem();
            if (selectedOrder != null) {
                this.selectedOrder = selectedOrder;
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

    public void deleteOrder(ActionEvent actionEvent) {
        if (selectedOrder != null) {
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
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle(Constants.SELECT_AN_ORDER);
            a.setHeaderText(null);
            a.setContentText(Constants.TO_DELETE_AN_ORDER_SELECT_IT_FIRST);
            a.show();
        }
    }
}
