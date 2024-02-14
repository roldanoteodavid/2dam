package org.example.app.ui.screens.common;

public enum Screens {

    PRINCIPAL ("/fxml/principal.fxml"),
    LOGIN ("/fxml/login.fxml"),
    RECURSOS ("/fxml/recursos.fxml");

    private String ruta;
    Screens(String ruta) {
        this.ruta=ruta;
    }
    public String getRuta(){return ruta;}


}
