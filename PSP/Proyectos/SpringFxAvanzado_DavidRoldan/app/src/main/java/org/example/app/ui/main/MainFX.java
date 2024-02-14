package org.example.app.ui.main;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.app.ui.screens.common.Screens;
import org.example.app.ui.screens.principal.PrincipalController;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MainFX implements ApplicationListener<DIJavafx.StageReadyEvent> {


    private final FXMLLoader fxmlLoader;

    public MainFX(FXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }


    @Override
    public void onApplicationEvent(DIJavafx.StageReadyEvent event) {
        try {
            Stage stage = event.getStage();
            Parent fxmlParent = fxmlLoader.load(getClass().getResourceAsStream(Screens.PRINCIPAL.getRuta()));
            PrincipalController controller = fxmlLoader.getController();
            controller.setStage(stage);

            stage.setScene(new Scene(fxmlParent));
            stage.show();
        } catch (IOException e) {
            System.exit(0);
        }
    }
}
