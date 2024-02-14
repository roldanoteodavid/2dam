package org.example.app.ui.screens.recursos;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.example.app.common.Constantes;
import org.example.app.domain.modelo.Recurso;
import org.example.app.ui.screens.common.BaseScreenController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecursosController extends BaseScreenController {
    private final RecursosViewModel recursosViewModel;
    @FXML
    private TableColumn<Integer, Recurso> idColumn;
    @FXML
    private TableColumn<String, Recurso> recursoColumn;
    @FXML
    private TextField recursoField;
    @FXML
    private TextField passField;
    @FXML
    private ComboBox<String> usersComboBox;
    @FXML
    private TableView<Recurso> recursosTable;
    @FXML
    private BorderPane recursosPane;

    private Recurso selectedRecurso;

    @Autowired
    public RecursosController(RecursosViewModel recursosViewModel) {
        this.recursosViewModel = recursosViewModel;
    }

    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID));
        recursoColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.NOMBRE));
        recursosTable.setOnMouseClicked(this::handleTableClick);


        recursosViewModel.getState().addListener((observable, oldValue, newValue) -> {
            if (newValue.error() != null) {
                Platform.runLater(() -> {
                    getPrincipalController().sacarAlertError(newValue.error(), Alert.AlertType.ERROR);
                    recursosViewModel.cleanState();
                });
            }
            if (newValue.mensaje() != null) {
                Platform.runLater(() -> {
                    getPrincipalController().sacarAlertError(newValue.mensaje(), Alert.AlertType.INFORMATION);
                    recursosViewModel.cleanState();
                });
            }
            if (newValue.isLoading()) {
                getPrincipalController().getRootPanel().setCursor(javafx.scene.Cursor.WAIT);
            } else {
                getPrincipalController().getRootPanel().setCursor(javafx.scene.Cursor.DEFAULT);
            }
            if (newValue.recursosList() != null) {
                Platform.runLater(() -> {
                    recursosTable.getItems().clear();
                    recursosTable.getItems().addAll(newValue.recursosList());
                });
            }
            if (newValue.usersList() != null) {
                Platform.runLater(() -> {
                    usersComboBox.getItems().clear();
                    usersComboBox.getItems().addAll(newValue.usersList());
                });
            }
            if (newValue.recurso() != null) {
                Platform.runLater(() -> {
                    recursoField.setText(newValue.recurso().getNombre());
                    passField.setText(newValue.recurso().getPassword());
                });
            }
        });
    }

    private void handleTableClick(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 1) {
            selectedRecurso = recursosTable.getSelectionModel().getSelectedItem();
            if (selectedRecurso != null) {
                recursosViewModel.getPassword(selectedRecurso.getId(), getPrincipalController().actualCredential.getUsername());
            }
        }
    }

    @Override
    public void principalCargado() {
        recursosViewModel.getRecursos(getPrincipalController().actualCredential.getUsername());
        recursosViewModel.getUsers(getPrincipalController().actualCredential.getUsername());
    }

    public void addRecurso() {
        if (recursoField.getText() == null || recursoField.getText().isEmpty() || passField.getText() == null || passField.getText().isEmpty()) {
            getPrincipalController().sacarAlertError(Constantes.DEBE_COMPLETAR_TODOS_LOS_CAMPOS, Alert.AlertType.ERROR);
        } else {
            recursosViewModel.addRecurso(new Recurso(0, recursoField.getText(), passField.getText(), null, getPrincipalController().actualCredential.getUsername(), null));
        }
    }

    public void cambiarPass() {
        if (selectedRecurso != null){
            recursosViewModel.cambiarPass(selectedRecurso.getId(), passField.getText(), getPrincipalController().actualCredential.getUsername());
        } else {
            getPrincipalController().sacarAlertError(Constantes.SELECCIONA_RECURSO, Alert.AlertType.ERROR);
        }
    }

    public void compartirRecurso() {
        if (selectedRecurso != null){
            if (usersComboBox.getSelectionModel().getSelectedItem() != null){
                recursosViewModel.compartirRecurso(selectedRecurso.getId(), usersComboBox.getSelectionModel().getSelectedItem(), getPrincipalController().actualCredential.getUsername());
            } else {
                getPrincipalController().sacarAlertError(Constantes.SELECCIONA_USUARIO, Alert.AlertType.ERROR);
            }
        } else {
            getPrincipalController().sacarAlertError(Constantes.SELECCIONA_RECURSO, Alert.AlertType.ERROR);
        }
    }

    public void comprobarFirma() {
        if (selectedRecurso != null){
            recursosViewModel.comprobarFirma(selectedRecurso.getId(), passField.getText());
        } else {
            getPrincipalController().sacarAlertError(Constantes.SELECCIONA_RECURSO, Alert.AlertType.ERROR);
        }
    }
}
