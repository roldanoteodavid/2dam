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

import java.util.concurrent.atomic.AtomicReference;

public class LoginController extends BaseScreenController {
    private final LoginService servicesLogin;
    @FXML
    private BorderPane loginPane;
    @FXML
    private TextField userTextField;
    @FXML
    private TextField passTextField;

    @Inject
    LoginController(LoginService servicesLogin) {
        this.servicesLogin = servicesLogin;
    }

    @FXML
    private void doLogin() {
        AtomicReference<Credential> credentialRef = new AtomicReference<>();

        servicesLogin.get(new Credential(null, userTextField.getText(), passTextField.getText()))
                .peek(credentialRef::set)
                .peekLeft(credentialError -> getPrincipalController().sacarAlertError(credentialError.getMessage()));

        Credential credential = credentialRef.get();

        if (credential != null) {
            getPrincipalController().onLoginDone(credential);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(Constants.INCORRECT_USER_OR_PASSWORD);
            a.show();
        }
    }
}
