package ui.pantallas.principal;


import LoadTXT.LoadTXT;
import LoadTXT.loadTXTImpl.LoadTXTImpl;
import game.demiurge.Demiurge;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import loaderManual.DungeonLoaderManual;
import lombok.extern.log4j.Log4j2;
import saveTXT.SaveTXT;
import saveTXT.saveTXTImpl.SaveTXTImpl;
import saveXML.SaveXML;
import saveXML.saveXMLImpl.SaveXMLImp;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.Pantallas;

import java.io.IOException;

@Log4j2
public class PrincipalController {
    Instance<Object> instance;

    private Stage primaryStage;

    @FXML
    public BorderPane root;


    private final Alert alert;

    @FXML
    private MenuBar menuPrincipal;

    public static Demiurge actualDemiurge;

    @Inject
    public PrincipalController(Instance<Object> instance) {
        this.instance = instance;
        alert = new Alert(Alert.AlertType.NONE);
    }

    private void cargarPantalla(Pantallas pantalla) {
        cambioPantalla(cargarPantalla(pantalla.getRuta()));
    }


    public void sacarAlertError(String mensaje) {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setContentText(mensaje);
        alert.getDialogPane().setId("alert");
        alert.getDialogPane().lookupButton(ButtonType.OK).setId("btn-ok");
        alert.showAndWait();
    }


    private Pane cargarPantalla(String ruta) {
        Pane panePantalla = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(controller -> instance.select(controller).get());
            panePantalla = fxmlLoader.load(getClass().getResourceAsStream(ruta));
            BasePantallaController pantallaController = fxmlLoader.getController();
            pantallaController.setPrincipalController(this);
            pantallaController.principalCargado();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return panePantalla;
    }

    private void cambioPantalla(Pane pantallaNueva) {
        root.setCenter(pantallaNueva);
    }


    public void initialize() {
        menuPrincipal.setVisible(false);
        cargarPantalla(Pantallas.WELCOME);
    }

    public void exit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar salida");
        alert.setHeaderText(null);
        alert.setContentText("¿Quieres salir sin guardar?");

        ButtonType buttonTypeYes = new ButtonType("Sí");
        ButtonType buttonTypeNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonTypeYes) {
                Platform.exit();
            } else if (buttonType == buttonTypeNo) {
                alert.close();
            }
        });
    }

    public void play() {
        cargarPantalla(Pantallas.ELEGIRPARTIDA);
        menuPrincipal.setVisible(true);
    }

    public void loadGame(Demiurge demiurge) {
        actualDemiurge = demiurge;
    }

    public void save(String filePath) {
        if (filePath.endsWith("xml")) {
            SaveXML saveXML = new SaveXMLImp();
            saveXML.saveDemiurge(actualDemiurge, filePath);
        } else if (filePath.endsWith("txt")) {
            SaveTXT saveTXT = new SaveTXTImpl();
            try {
                saveTXT.saveDemiurge(actualDemiurge, filePath);
            } catch (IOException e) {
                sacarAlertError("Error al guardar el Demiurge");
            }
        }else sacarAlertError("Error al guardar el archivo, escriba un nombre válido");
    }

    public void setStage(Stage stage) {
        primaryStage = stage;
    }


    public void save(ActionEvent actionEvent) {
        cargarPantalla(Pantallas.SAVE);
    }

    public Demiurge getActualDemiurge() {
        return actualDemiurge;
    }
    public void jugar(){
        cargarPantalla(Pantallas.JUGAR);
    }
}
