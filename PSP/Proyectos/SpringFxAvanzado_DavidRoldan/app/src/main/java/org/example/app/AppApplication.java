package org.example.app;

import javafx.application.Application;
import org.example.app.ui.main.DIJavafx;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication {

    public static void main(String[] args) {
        Application.launch(DIJavafx.class, args);
    }

}
