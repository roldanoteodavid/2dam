package ui.screens.login;

import common.Constants;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import model.Credential;
import services.LoginService;
import ui.screens.common.BaseScreenController;

public class LoginController extends BaseScreenController {
    public BorderPane loginPane;
    @FXML
    private TextField userTextField;
    @FXML
    private TextField passTextField;
    private final LoginService servicesLogin;

    @Inject
    LoginController(LoginService servicesLogin) {
        this.servicesLogin = servicesLogin;
    }

    @FXML
    private void doLogin() {
        Credential credential = new Credential(userTextField.getText(), passTextField.getText());
        if (servicesLogin.get(credential)) {
            getPrincipalController().onLoginDone(credential);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(Constants.INCORRECT_USER_OR_PASSWORD);
            a.show();
        }
    }
}
