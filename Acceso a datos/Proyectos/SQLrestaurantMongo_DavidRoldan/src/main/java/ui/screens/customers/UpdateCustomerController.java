package ui.screens.customers;

import common.Constants;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Customer;
import org.bson.types.ObjectId;
import services.CustomerService;
import ui.screens.common.BaseScreenController;

import java.io.IOException;
import java.time.LocalDate;

public class UpdateCustomerController extends BaseScreenController {
    private final CustomerService servicesCustomers;
    @FXML
    private TableView<Customer> customersTable;
    @FXML
    private TableColumn<ObjectId, Customer> idCustomerColumn;
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
    public UpdateCustomerController(CustomerService servicesCustomers) {
        this.servicesCustomers = servicesCustomers;
    }

    public void initialize() {
        idCustomerColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.M_ID));
        firstnameCustomerColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.FIRSTNAME));
        lastnameCustomerColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.LASTNAME));
        emailCustomerColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.EMAIL));
        phoneCustomerColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.PHONE));
        dobCustomerColumn.setCellValueFactory(new PropertyValueFactory<>(Constants.DOB));
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
                dobField.setValue(selectedCustomer.getDate_of_birth());
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
        servicesCustomers.update(new Customer(idField.getText(), fnameField.getText(), lnameField.getText(), emailField.getText(), phoneField.getText(), dobField.getValue())).peek(customerInt -> {
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

