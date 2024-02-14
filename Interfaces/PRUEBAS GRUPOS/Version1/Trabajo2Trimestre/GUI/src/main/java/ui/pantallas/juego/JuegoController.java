package ui.pantallas.juego;


import game.character.exceptions.WizardNotEnoughEnergyException;
import game.character.exceptions.WizardTiredException;
import game.demiurge.Demiurge;
import game.demiurge.DemiurgeHomeManager;
import game.dungeon.HomeNotEnoughSingaException;
import game.objectContainer.Container;
import game.objectContainer.exceptions.ContainerEmptyException;
import game.objectContainer.exceptions.ContainerErrorException;
import game.util.ValueOverMaxException;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import ui.pantallas.common.BasePantallaController;

import javafx.fxml.FXML;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static ui.pantallas.principal.PrincipalController.actualDemiurge;


public class JuegoController extends BasePantallaController {
    @FXML
    private ComboBox<String> doorsCombobox;
    @FXML
    private Label dungeonOptions;
    @FXML
    private TextArea answerArea;
    @FXML
    private Button answerButton;
    @FXML
    private TextArea consoleArea;
    @FXML
    private Button attackButton;

    @FXML
    private Button fleeButton;
    @FXML
    private ComboBox<String> typeAttack;
    @FXML
    private ComboBox<Integer> options;

    private boolean atHome;
    private int ubi;
    private Demiurge demiurge = actualDemiurge;
    DemiurgeHomeManager homeManager= demiurge.getHomeManager();

    public JuegoController() {

    }

    @FXML
    public void initialize() {
        atHome = true;
        if (atHome) {
            home();
        } else dungeon();
        consoleArea.setEditable(false);

        Integer[] optionsArray = {1, 2, 3, 4, 5, 6, 7};
        options.getItems().addAll(optionsArray);
        options.getItems().addAll();
        fleeButton.setVisible(false);
        attackButton.setVisible(false);
        typeAttack.setVisible(false);
        dungeonOptions.setVisible(false);
        answerArea.setVisible(false);
        answerButton.setVisible(false);
        doorsCombobox.setVisible(false);
    }

    private void home() {
        ubi = 0;
        writeConsole("------");
        writeConsole("-HOME-");
        writeConsole("------");
        writeConsole(demiurge.getHomeManager().homeInfo() + "\n");
        writeConsole(demiurge.getDungeonManager().wizardInfo() + "\n");
        writeConsole("Select: ");
        writeConsole("1.- Go to Dungeon");
        writeConsole("2.- Sleep (recover energy)");
        writeConsole("3.- Recover life points");
        writeConsole("4.- Merge crystals");
        writeConsole("5.- Upgrade characteristics");
        writeConsole("6.- Manage Spells");
        writeConsole("7.- Manage storage");
    }

    private void sleep() {
        writeConsole("Good night... zZzZzZzz");
        writeConsole(".");
        writeConsole(".");
        writeConsole(".");
        demiurge.nextDay();
        writeConsole("Good morning!\nDay: " + demiurge.getDay());
    }
    void recover() throws WizardTiredException{
        AtomicInteger points = new AtomicInteger();
        writeConsole("");
        writeConsole("----------------------");
        writeConsole("Recovering life points");
        writeConsole("----------------------");
        while (true) {
            if (homeManager.getLife() == homeManager.getLifeMax()) {
                writeConsole("You are full of life points.");
                break;
            } else {
                writeConsole("You have " + homeManager.getLife() + " of " + homeManager.getLifeMax() + " points of life.");
                writeConsole("The recovering cost is " + homeManager.getSingaPerLifePoint() + " of singa per life point, and actually there is " + homeManager.getSinga() + " singa.");
                writeConsole("How many points do you want to recover (0 to exit)? ");
                try {
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Recover Life Points");
                    dialog.setHeaderText(null);
                    dialog.setContentText("How many points do you want to recover (0 to exit)?");
                    dialog.showAndWait().ifPresent(userInput -> points.set(Integer.parseInt(userInput)));
                    if (points.get() == 0)
                        break;
                    else
                        homeManager.recover(points.get());
                } catch (NumberFormatException e) {
                    getPrincipalController().sacarAlertError("Only numbers.");
                } catch (HomeNotEnoughSingaException e) {
                    getPrincipalController().sacarAlertError("You don't have enough singa.");
                } catch (ValueOverMaxException e) {
                    getPrincipalController().sacarAlertError("You can't have that many life points.");
                }
            }
        }
    }
    private void mergeCristals() throws WizardTiredException{
        Container carrier = homeManager.getCarrier();
        int shift = 1;
        int crystals= carrier.size();
        int singaSpace=homeManager.getSingaSpace();
        writeConsole("----------------");
        writeConsole("Merging crystals");
        writeConsole("----------------");
        if(carrier.isEmpty()){
            writeConsole("No crystals to merge.");
        }else if(singaSpace==0){
            writeConsole("Your singa storage is full of singa.");
        }else {
            try {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Merging Crystals");
                dialog.setHeaderText(null);
                dialog.setContentText("Select crystal to merge (0 to exit):");
                ChoiceDialog<String> choiceDialog = new ChoiceDialog<>();
                choiceDialog.setTitle("Merging Crystals");
                choiceDialog.setHeaderText(null);
                choiceDialog.setContentText("Select crystal to merge (0 to exit):");
                List<String> crystalOptions = new ArrayList<>();
                crystalOptions.add("0");
                int pos = shift;
                Iterator it = carrier.iterator();
                while (it.hasNext())
                    crystalOptions.add((pos++) + ".- To merge " + it.next().toString());

                choiceDialog.getItems().addAll(crystalOptions);
                Optional<String> result = choiceDialog.showAndWait();

                if (result.isPresent()) {
                    int selectedCrystal = Integer.parseInt(result.get().split("\\.")[0]);
                    if (selectedCrystal > 0 && selectedCrystal <= crystals)
                        homeManager.mergeCrystal(selectedCrystal - shift);
                    else
                        getPrincipalController().sacarAlertError("Select a correct option.");
                }
            } catch (NumberFormatException e) {
                getPrincipalController().sacarAlertError("Only numbers.");
            } catch (ContainerEmptyException e) {
                getPrincipalController().sacarAlertError("No crystals to merge");
            } catch (ContainerErrorException e) {
                getPrincipalController().sacarAlertError("Impossible to merge crystals");
            }
        }
    }
    public void upgrades() throws WizardTiredException {
        AtomicInteger option = new AtomicInteger();

        writeConsole("---------");
        writeConsole("-Upgrade-");
        writeConsole("---------");
        while (true) {
            try {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Select:");
                dialog.setHeaderText(null);
                dialog.setContentText("0- Exit menu\n1- Upgrade max life\n2- Upgrade max energy\n3- Upgrade home comfort\n4- Upgrade stone capacity\nSo...:");

                dialog.showAndWait().ifPresent(userInput -> {
                    option.set(Integer.parseInt(userInput));
                });
                try {
                    switch (option.get()) {
                        case 0 -> {
                            return;
                        }
                        case 1 -> homeManager.upgradeLifeMax();
                        case 2 -> homeManager.upgradeEnergyMax();
                        case 3 -> homeManager.upgradeComfort();
                        case 4 -> homeManager.upgradeSingaMax();
                        default -> writeConsole("Select a correct option.");
                    }
                } catch (InputMismatchException e) {
                    writeConsole("Only numbers.");
                } catch (HomeNotEnoughSingaException e) {
                    writeConsole("You don't have enough singa.");
                } catch (WizardNotEnoughEnergyException e) {
                    writeConsole("You don't have enough energy to do that.");
                }
            } catch (NumberFormatException e) {
                getPrincipalController().sacarAlertError("Only numbers.");
            }
        }
    }
    private void dungeon() {
        ubi = 5;
    }

    @FXML
    private void send() {
        if (ubi == 0) {
            if(options.getValue()!=null) {
                try {
                    switch (options.getValue()) {
                        case 1:
                            dungeon();
                            break;
                        case 2:
                            sleep();
                            break;
                        case 3:
                            recover();
                            break;
                        case 4:
                            mergeCristals();
                            break;
                        case 5:
                            upgrades();
                            break;
                        case 6:
                            break;
                        case 7:
                            break;
                    }
                } catch (WizardTiredException e) {
                    getPrincipalController().sacarAlertError("You are tired, you need to sleep");
                    sleep();
                }
            }else{
                writeConsole("You haven't choose an option. Please choose a correct one");
            }
        }
    }
    @FXML
    private void onContinuePressed(ActionEvent actionEvent){
        if (ubi == 0) {
            home();
        } else dungeon();
    }
    private void writeConsole(String content){
        consoleArea.appendText(content+"\n");
    }
}

