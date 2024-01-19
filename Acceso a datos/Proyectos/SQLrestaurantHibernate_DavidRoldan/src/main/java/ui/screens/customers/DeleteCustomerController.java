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
    private TableView<Customer> customersTable;
    @FXML
    private TableColumn<Integer, Customer> idCustomerColumn;
    @FXML
    private TableColumn<String, Customer> firstnameCustomerColumn;
    @FXML
    private TableColumn<String, Customer> lastnameCustomerColumn;
    @FXML
    private TableColumn<String, Customer> emailCustomerColumn;
    @FXML
    private TableColumn<String, Customer> phoneCustomerColumn;
    @FXML
    private TableColumn<LocalDate, Customer> dobCustomerColumn;
    @FXML
    private TableView<Order> ordersTable;
    @FXML
    private TableColumn<Integer, Order> idOrderColumn;
    @FXML
    private TableColumn<Timestamp, Order> dateOrderColumn;
    @FXML
    private TableColumn<Integer, Order> customerOrderColumn;
    @FXML
    private TableColumn<Integer, Order> tableOrderColumn;
    private Customer selectedCustomer;

    @Inject
    public DeleteCustomerController(CustomerService servicesCustomers, OrderService servicesOrders) {
        this.servicesCustomers = servicesCustomers;
        this.servicesOrders = servicesOrders;
    }

    public void initialize() {
        idCustomerColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.ID));
        firstnameCustomerColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.FIRSTNAME));
        lastnameCustomerColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.LASTNAME));
        emailCustomerColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.EMAIL));
        phoneCustomerColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.PHONE));
        dobCustomerColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.DOB));
        customersTable.setOnMouseClicked(this::handleTableClick);
        idOrderColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.ID));
        dateOrderColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.DATE));
        customerOrderColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.CUSTOMER_ID));
        tableOrderColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.TABLE_ID));
    }

    private void handleTableClick(MouseEvent event) {
        if (event.getClickCount() == 1) {
            Customer newselectedCustomer = customersTable.getSelectionModel().getSelectedItem();
            if (newselectedCustomer != null) {
                this.selectedCustomer = newselectedCustomer;
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
            if (servicesOrders.hasOrder(selectedCustomer.getId())) {
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.getButtonTypes().remove(ButtonType.OK);
                alert2.getButtonTypes().add(ButtonType.CANCEL);
                alert2.getButtonTypes().add(ButtonType.YES);
                alert2.setTitle(Constants.DELETE_CUSTOMER_AND_ORDERS);
                alert2.setContentText(Constants.THE_SELECTED_CUSTOMER_HAS_ORDERS_DO_YOU_WANT_TO_DELETE_THE_CUSTOMER_AND_ITS_ORDERS);
                Optional<ButtonType> res2 = alert2.showAndWait();
                res2.ifPresent(buttonType2 -> {
                    if (buttonType2 == ButtonType.YES) {
                        servicesCustomers.delete(selectedCustomer, true).peek(customerInt -> {
                                    if (customerInt == 0) {
                                        Alert a = new Alert(AlertType.INFORMATION);
                                        a.setTitle(Constants.CUSTOMER_DELETED);
                                        a.setHeaderText(null);
                                        a.setContentText(Constants.THE_CUSTOMER_HAS_BEEN_DELETED_SUCCESSFULLY);
                                        a.show();
                                        setTables();
                                    }
                                })
                                .peekLeft(customerError2 -> getPrincipalController().sacarAlertError(customerError2.getMessage()));
                    }
                });
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.getButtonTypes().remove(ButtonType.OK);
                alert.getButtonTypes().add(ButtonType.CANCEL);
                alert.getButtonTypes().add(ButtonType.YES);
                alert.setTitle(Constants.DELETE_CUSTOMER);
                alert.setContentText(Constants.DO_YOU_WANT_TO_DELETE_THE_SELECTED_CUSTOMER);
                Optional<ButtonType> res = alert.showAndWait();
                res.ifPresent(buttonType -> {
                    if (buttonType == ButtonType.YES) {
                        servicesCustomers.delete(selectedCustomer, false).peek(customerInt -> {
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


