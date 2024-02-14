package ui.screen.home;

import console.ConsoleContainerManager;
import game.demiurge.DemiurgeHomeManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.screen.common.BaseScreenController;
import ui.screen.common.Screens;

public class HomeController extends BaseScreenController {

    public TextField inputField;
    DemiurgeHomeManager homeManager;
    ConsoleContainerManager cContainerManager;
    @FXML
    private TextArea textArea;
    @FXML
    private Label vidaLabel;
    @FXML
    private Label diaLabel;
    @FXML
    private ProgressBar vidaProgressBar;

    public void initialize() {
        textArea.setEditable(false);
        setHomeLabel();
    }

    private void setHomeLabel(){
        textArea.setText("Select" + "\n" + "1. Sleep (recover energy)" + "\n" + "2. Recover life points" + "\n" + "3. Merge crystals" + "\n" + "4. Upgrade characteristics" + "\n" + "5. Manage Spells" + "\n" + "6. Manage storage");
    }

    public void principalCargado() {
        // Do nothing
    }

    public void toDungeon() {
        getPrincipalController().cargarPantalla(Screens.GUIGAME);
    }

    public void manageStorage() {
        getPrincipalController().cargarPantalla(Screens.ITEMS);
    }

    public void enviar() {
      int option= Integer.parseInt(inputField.getText());
        switch (option) {
            case 1 -> {
//                textArea.setText("You have slept and recovered energy");
                getPrincipalController().sacarMessage("Zzz...");
                inputField.setText("");
              //  homeManager.sleep();
            }
            case 2 -> {
//                textArea.setText("You have recovered life points");
                getPrincipalController().sacarMessage("You have recovered life points");
                inputField.setText("");
               // homeManager.recoverLifePoints();
            }
            case 3 -> {
//                textArea.setText("You have merged crystals");
                getPrincipalController().sacarMessage("You have merged crystals");
                inputField.setText("");
                //homeManager.mergeCrystals();
            }
            case 4 -> {
//                textArea.setText("You have upgraded characteristics");
                getPrincipalController().sacarMessage("You have upgraded characteristics");
                inputField.setText("");
           //     homeManager.upgradeCharacteristics();
            }
            case 5 -> {
//                textArea.setText("You have managed spells");
                getPrincipalController().sacarMessage("You have managed spells");
                inputField.setText("");
             //   homeManager.manageSpells();
            }
            case 6 -> {
//                textArea.setText("You have managed storage");
                getPrincipalController().sacarMessage("You have managed storage");
                inputField.setText("");
              //  cContainerManager.home();
            }
            default -> getPrincipalController().sacarAlertError("Select a correct option.");
        }
    }
}
