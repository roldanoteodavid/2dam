package ui.screens.welcome;

import common.Constants;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import services.CustomerService;
import ui.screens.common.BaseScreenController;

public class WelcomeController extends BaseScreenController {

    @FXML
    private Label welcomeLabel;

    private final CustomerService customerService;

    @Inject
    public WelcomeController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void principalCargado() {
        welcomeLabel.setText(Constants.WELCOME + getPrincipalController().actualCredential.getUser_name());
    }

    @FXML
    public void convertToMongo() {
        customerService.transferToMongo().peek(integer -> getPrincipalController().sacarAlertInfo(Constants.TRANSFER_OK))
                .peekLeft(restaurantError -> getPrincipalController().sacarAlertError(restaurantError.getMessage()));
    }
}
