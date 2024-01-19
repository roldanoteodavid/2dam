package ui.screens.customers;

import common.Constants;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Credential;
import model.Customer;
import services.CustomerService;
import ui.screens.common.BaseScreenController;

import java.io.IOException;
import java.time.LocalDate;

public class AddCustomerController extends BaseScreenController {
    private final CustomerService servicesCustomers;
    @FXML
    private TextField passwordField;
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
    private TextField usernameField;
    @FXML
    private TextField fnameField;
    @FXML
    private TextField lnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private DatePicker dobField;

    @Inject
    public AddCustomerController(CustomerService servicesCustomers) {
        this.servicesCustomers = servicesCustomers;
    }

    public void initialize() {
        idCustomerColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.ID));
        firstnameCustomerColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.FIRSTNAME));
        lastnameCustomerColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.LASTNAME));
        emailCustomerColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.EMAIL));
        phoneCustomerColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.PHONE));
        dobCustomerColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.DOB));
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

    private void clearAll() {
        usernameField.clear();
        passwordField.clear();
        fnameField.clear();
        lnameField.clear();
        emailField.clear();
        phoneField.clear();
        dobField.setValue(null);
    }

    public void addCustomer() {
        if (usernameField.getText().isEmpty() || fnameField.getText().isEmpty() || lnameField.getText().isEmpty() || emailField.getText().isEmpty() || phoneField.getText().isEmpty() || dobField.getValue() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(Constants.THERE_IS_AN_EMPTY_FIELD);
            a.show();
        } else {
            Customer customer = new Customer(0, fnameField.getText(), lnameField.getText(), emailField.getText(), phoneField.getText(), dobField.getValue(), new Credential(0, usernameField.getText(), passwordField.getText()));
            servicesCustomers.save(customer).peek(customer1 -> {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText(Constants.CUSTOMER_ADDED);
                a.show();
                clearAll();
                setTable();
            }).peekLeft(customerError -> getPrincipalController().sacarAlertError(customerError.getMessage()));
        }
    }
}
