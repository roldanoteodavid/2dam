package ui.pantallas.common;

public enum Pantallas {

    WELCOME(ConstantesPantallas.WELCOME),
    ELEGIRPARTIDA(ConstantesPantallas.ELEGIR_PARTIDA),
    JUGAR(ConstantesPantallas.JUGAR),
    SAVE(ConstantesPantallas.SAVE);

    private String ruta;
    Pantallas(String ruta) {
        this.ruta=ruta;
    }
    public String getRuta(){return ruta;}


}
