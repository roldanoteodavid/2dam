package ui.screens.customers;

import common.Constants;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Customer;
import model.Order;
import services.CustomerService;
import services.OrderService;
import ui.screens.common.BaseScreenController;

import java.io.IOException;
import java.security.Timestamp;
import java.time.LocalDate;
import java.util.Optional;

public class DeleteCustomerController extends BaseScreenController {
    private final CustomerService servicesCustomers;
    private final OrderService servicesOrders;
    @FXML
    public TableView<Customer> customersTable;
    @FXML
    public TableColumn<Integer, Customer> idCustomerColumn;
    @FXML
    public TableColumn<String, Customer> firstnameCustomerColumn;
    @FXML
    public TableColumn<String, Customer> lastnameCustomerColumn;
    @FXML
    public TableColumn<String, Customer> emailCustomerColumn;
    @FXML
    public TableColumn<String, Customer> phoneCustomerColumn;
    @FXML
    public TableColumn<LocalDate, Customer> dobCustomerColumn;
    public TableView<Order> ordersTable;
    public TableColumn<Integer, Order> idOrderColumn;
    public TableColumn<Timestamp, Order> dateOrderColumn;
    public TableColumn<Integer, Order> customerOrderColumn;
    public TableColumn<Integer, Order> tableOrderColumn;
    private Customer selectedCustomer;

    @Inject
    public DeleteCustomerController(CustomerService servicesCustomers, OrderService servicesOrders) {
        this.servicesCustomers = servicesCustomers;
        this.servicesOrders = servicesOrders;
    }

    public void initialize() {
        idCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstnameCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        lastnameCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        emailCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        dobCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("dob"));
        customersTable.setOnMouseClicked(this::handleTableClick);
        idOrderColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateOrderColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        customerOrderColumn.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        tableOrderColumn.setCellValueFactory(new PropertyValueFactory<>("table_id"));
    }

    private void handleTableClick(MouseEvent event) {
        if (event.getClickCount() == 1) {
            Customer selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
            if (selectedCustomer != null) {
                this.selectedCustomer = selectedCustomer;
                ordersTable.getItems().clear();
                ordersTable.getItems().addAll(servicesOrders.getOrdersByCustomerId(selectedCustomer.getId()));
            }
        }
    }

    @Override
    public void principalCargado() throws IOException {
        setTables();
    }

    private void setTables() {
        customersTable.getItems().clear();
        servicesCustomers.getAll().peek(customers -> customersTable.getItems().addAll(customers))
                .peekLeft(customerError -> getPrincipalController().sacarAlertError(customerError.getMessage()));
    }

    public void deleteCustomer() {
        if (selectedCustomer != null) {
            if (servicesOrders.getOrdersByCustomerId(selectedCustomer.getId()).isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.getButtonTypes().remove(ButtonType.OK);
                alert.getButtonTypes().add(ButtonType.CANCEL);
                alert.getButtonTypes().add(ButtonType.YES);
                alert.setTitle(Constants.DELETE_CUSTOMER);
                alert.setContentText(Constants.DO_YOU_WANT_TO_DELETE_THE_SELECTED_CUSTOMER);
                Optional<ButtonType> res = alert.showAndWait();
                res.ifPresent(buttonType -> {
                    if (buttonType == ButtonType.YES) {
                        servicesCustomers.delete(selectedCustomer).peek(customerInt -> {
                                    if (customerInt == 0) {
                                        Alert a = new Alert(AlertType.INFORMATION);
                                        a.setTitle(Constants.CUSTOMER_DELETED);
                                        a.setHeaderText(null);
                                        a.setContentText(Constants.THE_CUSTOMER_HAS_BEEN_DELETED_SUCCESSFULLY);
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
                alert.setTitle(Constants.DELETE_CUSTOMER_AND_ORDERS);
                alert.setContentText(Constants.THE_SELECTED_CUSTOMER_HAS_ORDERS_DO_YOU_WANT_TO_DELETE_THE_CUSTOMER_AND_ITS_ORDERS);
                Optional<ButtonType> res = alert.showAndWait();
                res.ifPresent(buttonType -> {
                    if (buttonType == ButtonType.YES) {
                        servicesOrders.deleteOrdersByCustomerId(selectedCustomer.getId());
                        servicesCustomers.delete(selectedCustomer).peek(customerInt -> {
                                    if (customerInt == 0) {
                                        Alert a = new Alert(AlertType.INFORMATION);
                                        a.setTitle(Constants.CUSTOMER_DELETED);
                                        a.setHeaderText(null);
                                        a.setContentText(Constants.THE_CUSTOMER_HAS_BEEN_DELETED_SUCCESSFULLY);
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
            a.setTitle(Constants.SELECT_A_CUSTOMER);
            a.setHeaderText(null);
            a.setContentText(Constants.TO_DELETE_A_CUSTOMER_SELECT_IT_FIRST);
            a.show();
        }
    }
}
