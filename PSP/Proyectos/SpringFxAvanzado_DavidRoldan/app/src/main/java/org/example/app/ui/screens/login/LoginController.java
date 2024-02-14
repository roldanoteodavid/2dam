package org.example.app.ui.screens.login;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.example.app.domain.modelo.Credentials;
import org.example.app.ui.screens.common.BaseScreenController;
import org.springframework.stereotype.Component;

@Component
public class LoginController extends BaseScreenController {
    private final LoginViewModel loginViewModel;
    @FXML
    private BorderPane loginPane;
    @FXML
    private TextField userTextField;
    @FXML
    private TextField passTextField;

    LoginController(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
    }

    public void initialize() {
        loginViewModel.getState().addListener((observable, oldValue, newValue) -> {
            if (newValue.loginOK()) {
                Credentials credential = new Credentials(0, userTextField.getText(), passTextField.getText());
                getPrincipalController().onLoginDone(credential);
            }
            if (newValue.error() != null) {
                Platform.runLater(() -> {
                    getPrincipalController().sacarAlertError(newValue.error(), Alert.AlertType.ERROR);
                    loginViewModel.cleanState();
                });
            }
            if (newValue.mensaje() != null) {
                Platform.runLater(() -> {
                    getPrincipalController().sacarAlertError(newValue.mensaje(), Alert.AlertType.INFORMATION);
                    loginViewModel.cleanState();
                });
            }
            if (newValue.isLoading()){
                getPrincipalController().getRootPanel().setCursor(Cursor.WAIT);
            }else {
                getPrincipalController().getRootPanel().setCursor(Cursor.DEFAULT);
            }

        });
    }

    @FXML
    private void doLogin() {
        loginViewModel.doLogin(userTextField.getText(), passTextField.getText());
    }

    @FXML
    private void doSignIn() {
        loginViewModel.doSignIn(new Credentials(1, userTextField.getText(), passTextField.getText()));

    }
}
