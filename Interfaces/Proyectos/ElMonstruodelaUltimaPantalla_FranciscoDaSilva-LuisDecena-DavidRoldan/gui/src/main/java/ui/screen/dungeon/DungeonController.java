package ui.screen.dungeon;

import console.ConsoleContainerManager;
import game.demiurge.DemiurgeDungeonManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import ui.screen.common.BaseScreenController;

import java.util.Scanner;

public class DungeonController extends BaseScreenController {
    @FXML
    private TextArea infoAcciones;
    @FXML
    private ComboBox goTo;
    @FXML
    private ProgressBar vida;
    @FXML
    private ComboBox gatherCrystal;
    @FXML
    private ComboBox upgrade;
    @FXML
    private ComboBox goToRoom;
    @FXML
    private ComboBox fightMonster;
    @FXML
    private ComboBox battleActions;
    @FXML
    private ComboBox attacks;
    Scanner sn;
    DemiurgeDungeonManager dDungeonManager;
    ConsoleContainerManager cContainerManager;
    public void initialize(){
        upgrade.setDisable(true);
        goToRoom.setDisable(true);
        battleActions.setDisable(true);
        attacks.setDisable(true);

    }
    public void goToManageItems(ActionEvent actionEvent) {
    }
}
