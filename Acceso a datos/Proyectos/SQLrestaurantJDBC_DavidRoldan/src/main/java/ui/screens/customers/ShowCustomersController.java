package ui.screens.customers;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import services.CustomerService;
import ui.screens.common.BaseScreenController;

import java.io.IOException;
import java.time.LocalDate;

public class ShowCustomersController extends BaseScreenController {
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

    @Inject
    public ShowCustomersController(CustomerService servicesCustomers) {
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
        setTables();
    }

    private void setTables() {
        customersTable.getItems().clear();
        servicesCustomers.getAll().peek(customers -> customersTable.getItems().addAll(customers))
                .peekLeft(customerError -> getPrincipalController().sacarAlertError(customerError.getMessage()));
    }
}
