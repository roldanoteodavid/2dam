package ui.pantallas.common;

public enum Pantallas {

    PRINCIPAL("/fxml/principal.fxml"), CHARACTERS("/fxml/characters.fxml");

    private final String ruta;

    Pantallas(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }


}
