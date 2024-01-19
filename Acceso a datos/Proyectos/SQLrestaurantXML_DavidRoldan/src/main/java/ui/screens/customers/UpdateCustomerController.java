package ui.screens.customers;

import common.Constants;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Customer;
import services.CustomerService;
import ui.screens.common.BaseScreenController;

import java.io.IOException;
import java.time.LocalDate;

public class UpdateCustomerController extends BaseScreenController {
    private final CustomerService servicesCustomers;
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
    public TextField idField;
    public TextField fnameField;
    public TextField lnameField;
    public TextField emailField;
    public TextField phoneField;
    public DatePicker dobField;

    @Inject
    public UpdateCustomerController(CustomerService servicesCustomers) {
        this.servicesCustomers = servicesCustomers;
    }

    public void initialize() throws IOException {
        idCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstnameCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        lastnameCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        emailCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        dobCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("dob"));
        customersTable.setOnMouseClicked(this::handleTableClick);
        idField.setDisable(true);
    }

    private void handleTableClick(MouseEvent event) {
        if (event.getClickCount() == 1) {
            Customer selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
            if (selectedCustomer != null) {
                idField.setText(String.valueOf(selectedCustomer.getId()));
                fnameField.setText(selectedCustomer.getFirst_name());
                lnameField.setText(selectedCustomer.getLast_name());
                emailField.setText(selectedCustomer.getEmail());
                phoneField.setText(selectedCustomer.getPhone());
                dobField.setValue(selectedCustomer.getDob());
            }
        }
    }

    @Override
    public void principalCargado() throws IOException {
        setTable();
    }

    private void setTable() {
        customersTable.getItems().clear();
        servicesCustomers.getAll().peek(customers -> customersTable.getItems().addAll(customers))
                .peekLeft(customerError -> getPrincipalController().sacarAlertError(customerError.getMessage()));
    }

    public void updateCustomer() {
        servicesCustomers.update(new Customer(Integer.parseInt(idField.getText()), fnameField.getText(), lnameField.getText(), emailField.getText(), phoneField.getText(), dobField.getValue())).peek(customerInt -> {
                    if (customerInt == 0) {
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setTitle(Constants.CUSTOMER_UPDATED);
                        a.setHeaderText(null);
                        a.setContentText(Constants.CUSTOMER_ADDED_SUCCESSFULLY);
                        a.show();
                        setTable();
                    }
                })
                .peekLeft(customerError -> getPrincipalController().sacarAlertError(customerError.getMessage()));
    }
}

