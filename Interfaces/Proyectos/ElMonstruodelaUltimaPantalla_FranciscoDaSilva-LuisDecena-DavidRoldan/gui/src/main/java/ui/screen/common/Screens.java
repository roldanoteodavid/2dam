package ui.screen.common;

public enum Screens {

    PRINCIPAL ("/fxml/principal.fxml"),
    HOME ("/fxml/guiHome.fxml"),
    ITEMS ("/fxml/manageItems.fxml"),
    GUIGAME ("/fxml/guiGame.fxml");

    private String ruta;
    Screens(String ruta) {
        this.ruta=ruta;
    }
    public String getRuta(){return ruta;}


}
