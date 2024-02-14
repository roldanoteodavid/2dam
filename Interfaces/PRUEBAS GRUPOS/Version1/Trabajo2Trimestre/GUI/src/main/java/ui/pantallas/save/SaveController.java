package ui.pantallas.save;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import ui.pantallas.common.BasePantallaController;

import java.io.File;

public class SaveController extends BasePantallaController {
    @Inject
    public SaveController(){}

    @FXML
    void saveXML() {
        save("xml");
    }

    @FXML
    void saveTXT() {
        save("txt");
    }

    private void save(String format) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save File");
            FileChooser.ExtensionFilter extFilter;
            if (format.equals("xml")) {
                extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
            } else {
                extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            }
            fileChooser.getExtensionFilters().add(extFilter);
            File selectedFile = fileChooser.showSaveDialog(null);
            if (selectedFile != null) {
                getPrincipalController().save(selectedFile.getAbsolutePath());
            }else getPrincipalController().sacarAlertError("Debe darle un nombre al archivo");
    }

    private void showAlertError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
