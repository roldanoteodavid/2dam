package ui.screens.customers;

import common.Constants;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import services.CustomerService;
import ui.screens.common.BaseScreenController;

import java.io.IOException;
import java.time.LocalDate;

public class AddCustomerController extends BaseScreenController {
    private final CustomerService servicesCustomers;
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
    private TextField idField;
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
        idCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstnameCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastnameCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        emailCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        dobCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("dob"));
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
        idField.clear();
        fnameField.clear();
        lnameField.clear();
        emailField.clear();
        phoneField.clear();
        dobField.setValue(null);
    }

    public void addCustomer() {
        if (idField.getText().isEmpty() || fnameField.getText().isEmpty() || lnameField.getText().isEmpty() || emailField.getText().isEmpty() || phoneField.getText().isEmpty() || dobField.getValue() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(Constants.THERE_IS_AN_EMPTY_FIELD);
            a.show();
        } else {
            Customer customer = new Customer(Integer.parseInt(idField.getText()), fnameField.getText(), lnameField.getText(), emailField.getText(), phoneField.getText(), dobField.getValue());
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
