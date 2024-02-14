package ui.screens.customers;

import common.Constants;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import org.bson.types.ObjectId;
import services.CustomerService;
import ui.screens.common.BaseScreenController;

import java.io.IOException;
import java.time.LocalDate;

public class ShowCustomersController extends BaseScreenController {
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

    @Inject
    public ShowCustomersController(CustomerService servicesCustomers) {
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
        setTables();
    }

    private void setTables() {
        customersTable.getItems().clear();
        servicesCustomers.getAll().peek(customers -> customersTable.getItems().addAll(customers))
                .peekLeft(customerError -> getPrincipalController().sacarAlertError(customerError.getMessage()));
    }
}
