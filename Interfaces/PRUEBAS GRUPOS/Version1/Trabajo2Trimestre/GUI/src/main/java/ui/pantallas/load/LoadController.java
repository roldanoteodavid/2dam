package ui.pantallas.load;

import LoadTXT.LoadTXT;
import LoadTXT.loadTXTImpl.LoadTXTImpl;
import LoadXML.LoadXml;
import LoadXML.loadXMLImpl.LoadXMLImpl;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ui.pantallas.common.BasePantallaController;

import java.io.File;
import java.io.IOException;

public class LoadController extends BasePantallaController {
    @FXML
    Button loadXML;
    @FXML
    Button loadTXT;
    @FXML
    Label mensaje;
    @Inject
    public LoadController(){
    }
    @FXML
    private Stage primaryStage;

    @FXML
    void loadXML() {
        loadFile("XML files (*.xml)", "*.xml");
    }

    @FXML
    void loadTXT() {
        loadFile("TXT files (*.txt)", "*.txt");
    }

    private void loadFile(String extensionDescription, String fileExtension) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(extensionDescription, fileExtension);
        fileChooser.getExtensionFilters().add(extFilter);

        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            System.out.println(filePath);
            if (!filePath.toLowerCase().endsWith(fileExtension.substring(1))) {
                showAlertError("Seleccione un archivo válido con extensión " + fileExtension);
            } else {
                load(filePath);
            }
        }
    }
    private void load(String filepath){
        try{
            if(filepath.endsWith(".xml")){
                LoadXml loadXML=new LoadXMLImpl();
                getPrincipalController().loadGame(loadXML.load(filepath));
                mensaje.setText("Archivo XML cargado correctamente");
            }else {
                LoadTXT loadTXT=new LoadTXTImpl();
                getPrincipalController().loadGame(loadTXT.loadDemiurge(filepath));
                mensaje.setText("Archivo TXT cargado correctamente");
            }
        } catch (IOException | ClassNotFoundException e) {
            mensaje.setText("Error al cargar la mazmorra, revise el archivo seleccionado");

        }

    }
    private void showAlertError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void newGame(ActionEvent actionEvent) {
        if(getPrincipalController().getActualDemiurge()==null){
            getPrincipalController().sacarAlertError("No hay mazmorra cargada");
        }else{
            getPrincipalController().jugar();
        }
    }

    public void load(ActionEvent actionEvent) {
        loadXML.setVisible(true);
        loadTXT.setVisible(true);
    }
}
