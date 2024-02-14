package ui.pantallas.elegirPartida;

import LoadTXT.LoadTXT;
import LoadTXT.loadTXTImpl.LoadTXTImpl;
import LoadXML.LoadXml;
import LoadXML.loadXMLImpl.LoadXMLImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import ui.pantallas.common.BasePantallaController;

import java.io.File;
import java.io.IOException;

public class ElegirPartida extends BasePantallaController {
    @FXML
    private Label filePathLabel;
    @FXML
    private void elegirArchivo(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Archivo");
        FileChooser.ExtensionFilter xmlFilter = new FileChooser.ExtensionFilter("Archivos XML (*.xml)", "*.xml");
        FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("Archivos TXT (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().addAll(xmlFilter, txtFilter);

        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            if(filePath.endsWith(".xml")){
                LoadXml cargarXML=new LoadXMLImpl();
                cargarXML.load(filePath);
            }else{
                LoadTXT cargarTXT=new LoadTXTImpl();
                try{
                    cargarTXT.loadDemiurge(filePath);
                }catch (IOException | ClassNotFoundException ex){
                    getPrincipalController().sacarAlertError("Error al cargar el archivo");
                }
            }
            filePathLabel.setText("Ruta del Archivo: " + filePath);

        } else {
            getPrincipalController().sacarAlertError("Ningun archivo fue seleccionado");
        }
    }

}
