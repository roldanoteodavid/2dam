package org.example.app.ui.screens.principal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lombok.extern.log4j.Log4j2;
import org.example.app.common.Constantes;
import org.example.app.domain.modelo.Credentials;
import org.example.app.ui.screens.common.BaseScreenController;
import org.example.app.ui.screens.common.Screens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Log4j2
@Component
public class PrincipalController extends BaseScreenController {
    private Alert alert;
    public Credentials actualCredential;
    @FXML
    public BorderPane root;
    private final ApplicationContext context;
    private Stage primaryStage;
    @FXML
    private MenuBar menuPrincipal;

    @Autowired
    public PrincipalController(ApplicationContext context) {
        this.context = context;
    }

    public void initialize() {
        alert = new Alert(Alert.AlertType.NONE);
        menuPrincipal.setVisible(false);
        cargarPantalla(Screens.LOGIN);
    }

    private void cargarPantalla(Screens pantalla) {
        cambioPantalla(cargarPantalla(pantalla.getRuta()));
    }

    private Pane cargarPantalla(String ruta) {
        Pane panePantalla = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(context::getBean);
            panePantalla = fxmlLoader.load(getClass().getResourceAsStream(ruta));
            BaseScreenController pantallaController = fxmlLoader.getController();
            pantallaController.setPrincipalController(this);
            pantallaController.principalCargado();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return panePantalla;
    }

    public void sacarAlertError(String mensaje, Alert.AlertType alertType) {
        alert.setAlertType(alertType);
        alert.setContentText(mensaje);
        alert.getDialogPane().setId(Constantes.ALERT);
        alert.getDialogPane().lookupButton(ButtonType.OK).setId(Constantes.BTNOK);
        alert.show();
    }

    public void onLoginDone(Credentials credential) {
        actualCredential = credential;
        menuPrincipal.setVisible(true);
        cargarPantalla(Screens.RECURSOS);
    }

    public void logout() {
        actualCredential = null;
        menuPrincipal.setVisible(false);
        cargarPantalla(Screens.LOGIN);
    }

    private void cambioPantalla(Pane pantallaNueva) {
        root.setCenter(pantallaNueva);
    }


    public void exit() {
        primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public void setStage(Stage stage) {
        primaryStage = stage;
    }

    public BorderPane getRootPanel() {
        return root;
    }
}
