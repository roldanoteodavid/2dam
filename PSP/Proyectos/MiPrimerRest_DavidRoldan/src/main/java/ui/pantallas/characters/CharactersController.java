package ui.pantallas.characters;

import common.Constantes;
import domain.modelo.characters.Characters;
import domain.modelo.episodes.Episodes;
import domain.usecases.*;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import ui.pantallas.common.BasePantallaController;

import java.io.IOException;
import java.util.List;

public class CharactersController extends BasePantallaController {

    private final GetCharactersUseCase getCharactersUseCase;
    private final GetCharactersByNameUseCase getCharactersByNameUseCase;
    private final GetCharactersBySpeciesUseCase getCharactersBySpeciesUseCase;
    private final GetEpisodesByUrlUseCase getEpisodesByUrlUseCase;
    private final GetSpeciesUseCase getSpeciesUseCase;
    @FXML
    private TableView<Characters> charactersTable;
    @FXML
    private TableColumn<String, Characters> nameCharacterColumn;
    @FXML
    private TableColumn<String, Characters> statusCharacterColumn;
    @FXML
    private TableColumn<String, Characters> speciesCharacterColumn;
    @FXML
    private TableColumn<String, Characters> typeCharacterColumn;
    @FXML
    private TableColumn<String, Characters> genderCharacterColumn;
    @FXML
    private ComboBox<String> filterComboBox;
    @FXML
    private Button filterButton;
    @FXML
    private ImageView characterImg;
    @FXML
    private ComboBox<String> speciesComboBox;
    @FXML
    private TextField nameCharacterField;
    @FXML
    private TableView<Episodes> episodesTable;
    @FXML
    private TableColumn<String, Episodes> episodeColumn;
    @FXML
    private TableColumn<String, Episodes> nameEpisodeColumn;

    @Inject
    public CharactersController(GetCharactersUseCase getCharactersUseCase, GetCharactersByNameUseCase getCharactersByNameUseCase, GetCharactersBySpeciesUseCase getCharactersBySpeciesUseCase, GetEpisodesByUrlUseCase getEpisodesByUrlUseCase, GetSpeciesUseCase getSpeciesUseCase) {
        this.getCharactersUseCase = getCharactersUseCase;
        this.getCharactersByNameUseCase = getCharactersByNameUseCase;
        this.getCharactersBySpeciesUseCase = getCharactersBySpeciesUseCase;
        this.getEpisodesByUrlUseCase = getEpisodesByUrlUseCase;
        this.getSpeciesUseCase = getSpeciesUseCase;
    }

    public void initialize() {
        nameCharacterColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.NAME));
        statusCharacterColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.STATUS));
        speciesCharacterColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.SPECIES));
        typeCharacterColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.TYPE));
        genderCharacterColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.GENDER));

        nameEpisodeColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.NAME));
        episodeColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.EPISODE));

        filterComboBox.getItems().addAll(Constantes.SPECIES1, Constantes.NAME1, Constantes.NONE);

        List<String> allTypes = getSpeciesUseCase.getAllCharacterSpecies();
        speciesComboBox.getItems().addAll(allTypes);

        filterButton.setVisible(false);
        speciesComboBox.setVisible(false);
        nameCharacterField.setVisible(false);
        charactersTable.setOnMouseClicked(this::handleTableClick);
        filterComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (Constantes.SPECIES1.equals(newValue)) {
                speciesComboBox.setVisible(true);
                nameCharacterField.setVisible(false);
                filterButton.setVisible(true);
            } else if (Constantes.NAME1.equals(newValue)) {
                speciesComboBox.setVisible(false);
                nameCharacterField.setVisible(true);
                filterButton.setVisible(true);
            } else if (Constantes.NONE.equals(newValue)) {
                speciesComboBox.setVisible(false);
                nameCharacterField.setVisible(false);
                filterButton.setVisible(false);
                setTables();
            }
        });
    }

    private void handleTableClick(MouseEvent event) {
        if (event.getClickCount() == 1) {
            Characters character = charactersTable.getSelectionModel().getSelectedItem();
            if (character != null) {
                characterImg.setImage(new Image(character.getImage()));
                episodesTable.getItems().clear();
                getEpisodesByUrlUseCase.getEpisodesByUrl(character.getEpisode()).peek(episodes -> episodesTable.getItems().addAll(episodes)).peekLeft(error -> getPrincipalController().sacarAlertError(error));
            }

        }
    }

    @Override
    public void principalCargado() throws IOException {
        setTables();
    }

    private void setTables() {
        charactersTable.getItems().clear();
        getCharactersUseCase.getCharacters().peek(characters -> charactersTable.getItems().addAll(characters)).peekLeft(error -> getPrincipalController().sacarAlertError(error));
    }

    public void filterCharacters() {
        if (speciesComboBox.isVisible()) {
            if (speciesComboBox.getValue() != null) {
                charactersTable.getItems().clear();
                getCharactersBySpeciesUseCase.getCharactersBySpecies(speciesComboBox.getValue()).peek(characters -> charactersTable.getItems().addAll(characters)).peekLeft(error -> getPrincipalController().sacarAlertError(error));
            }
        } else if (nameCharacterField.isVisible() && nameCharacterField.getText() != null) {
            charactersTable.getItems().clear();
            getCharactersByNameUseCase.getCharactersByName(nameCharacterField.getText()).peek(characters -> charactersTable.getItems().addAll(characters)).peekLeft(error -> getPrincipalController().sacarAlertError(error));

        }
    }
}
