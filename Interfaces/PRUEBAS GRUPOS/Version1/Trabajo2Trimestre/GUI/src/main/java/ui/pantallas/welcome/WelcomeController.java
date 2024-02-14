package ui.pantallas.welcome;

import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import ui.pantallas.common.BasePantallaController;

public class WelcomeController extends BasePantallaController{

    @Inject
    public WelcomeController(){}

    public void exit(ActionEvent actionEvent) {
        Platform.exit();
    }
    public void loadGame(ActionEvent actionEvent){getPrincipalController().play();}
}
