package ui.screen.principal;


import common.Constants;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ui.screen.common.BaseScreenController;
import ui.screen.common.Screens;

import java.io.IOException;
import java.util.Optional;

public class PrincipalController extends BaseScreenController {
    private final Alert alert;
    @FXML
    public BorderPane root;
    @FXML
    private Menu menuGuardar;
    Instance<Object> instance;
    private Stage primaryStage;


    @Inject
    public PrincipalController(Instance<Object> instance) {
        this.instance = instance;
        alert = new Alert(Alert.AlertType.NONE);
    }

    public void cargarPantalla(Screens pantalla) {
        cambioPantalla(cargarPantalla(pantalla.getRuta()));
    }

    private Pane cargarPantalla(String ruta) {
        Pane panePantalla = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(controller -> instance.select(controller).get());
            panePantalla = fxmlLoader.load(getClass().getResourceAsStream(ruta));
            BaseScreenController pantallaController = fxmlLoader.getController();
            pantallaController.setPrincipalController(this);
            pantallaController.principalCargado();
        } catch (IOException e) {
//            log.error(e.getMessage(), e);
        }
        return panePantalla;
    }

    public void sacarAlertError(String mensaje) {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setContentText(mensaje);
        alert.getDialogPane().setId("alert");
        alert.getDialogPane().lookupButton(ButtonType.OK).setId(Constants.BTN_OK);
        alert.showAndWait();
    }

    public void sacarMessage(String mensaje) {
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setContentText(mensaje);
        alert.getDialogPane().setId("alert");
        alert.getDialogPane().lookupButton(ButtonType.OK).setId(Constants.BTN_OK);
        alert.showAndWait();
    }


    private void cambioPantalla(Pane pantallaNueva) {
        root.setCenter(pantallaNueva);
    }


    public void initialize() {
        cargarPantalla(Screens.HOME);
    }

    private void closeWindowEvent(WindowEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getButtonTypes().remove(ButtonType.OK);
        alert.getButtonTypes().add(ButtonType.CANCEL);
        alert.getButtonTypes().add(ButtonType.YES);
        alert.setTitle(Constants.QUIT_APPLICATION);
        alert.setContentText(Constants.CLOSE_WITHOUT_SAVING);
        alert.initOwner(primaryStage.getOwner());
        Optional<ButtonType> res = alert.showAndWait();
        res.ifPresent(buttonType -> {
            if (buttonType == ButtonType.CANCEL) {
                event.consume();
            }
        });
    }

    public void exit() {
        primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public void setStage(Stage stage) {
        primaryStage = stage;
        primaryStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
    }

    public void menuGuardar(ActionEvent actionEvent) {
        switch (((MenuItem) actionEvent.getSource()).getId()){
            case "guardarTXT":
                //TODO
                break;
            case "guardarXML":
                //TODO
                break;
        }
    }

    public void menuCargar(ActionEvent actionEvent) {
        switch (((MenuItem) actionEvent.getSource()).getId()){
            case "cargarTXT":
                //TODO
                break;
            case "cargarXML":
                //TODO
                break;
        }
    }

    public void menuGenerarInforme(ActionEvent actionEvent) {
        switch (((MenuItem) actionEvent.getSource()).getId()){
            case "generarInforme":
                //TODO
                break;
        }
    }
}
