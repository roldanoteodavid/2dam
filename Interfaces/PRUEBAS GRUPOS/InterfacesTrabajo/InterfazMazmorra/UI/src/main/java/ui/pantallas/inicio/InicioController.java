package ui.pantallas.inicio;

import game.character.exceptions.WizardTiredException;
import game.demiurge.DemiurgeDungeonManager;
import game.demiurge.DemiurgeHomeManager;
import game.demiurge.exceptions.EndGameException;
import game.demiurge.exceptions.GoDungeonException;
import game.dungeon.HomeNotEnoughSingaException;
import game.util.ValueOverMaxException;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import ui.pantallas.common.BasePantallaController;

public class InicioController extends BasePantallaController {

    //    @FXML
//    private ImageView imagenView;

     DemiurgeHomeManager homeManager;
    DemiurgeDungeonManager dungeonManager;


    public InicioController() {



    }

    public void initialize() {
//        double nuevoAncho = 800; // ajusta el valor según tus necesidades
//        double nuevoAlto = 750;
//        // Puedes cargar una imagen en la inicialización del controlador
//        Image imagen = new Image(getClass().getResource("/images/sala.jpeg").toExternalForm(), nuevoAncho, nuevoAlto, true, true);
//        imagenView.setImage(imagen);
    }

    public void exit(ActionEvent actionEvent) {
        getPrincipalController().exit(actionEvent);
    }

    public void goToDungeon(ActionEvent actionEvent) throws GoDungeonException, WizardTiredException, EndGameException {
        dungeonManager.enterDungeon();
    }

    public void sleep(ActionEvent actionEvent) throws WizardTiredException, ValueOverMaxException, HomeNotEnoughSingaException {
        homeManager.recover(20);

        getPrincipalController().showConfirmationAlert("Zzz...");
    }

    public void recoverLifePoints(ActionEvent actionEvent) throws WizardTiredException, ValueOverMaxException, HomeNotEnoughSingaException {
        homeManager.recover(10);
    }

    public void mergeCrystals(ActionEvent actionEvent) {
    }

    public void upgradeChracteristics(ActionEvent actionEvent) {
    }

    public void manageSpells(ActionEvent actionEvent) {
    }

    public void manageStorage(ActionEvent actionEvent) {
    }
}
