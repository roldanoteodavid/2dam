module GUI {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    requires lombok;
    requires org.apache.logging.log4j;

    requires jakarta.inject;
    requires jakarta.cdi;
    requires io.vavr;
    requires io.reactivex.rxjava3;
    requires org.pdfsam.rxjavafx;
    requires java.logging;
    requires java.sql;
    requires CargarTXT;
    requires CargarXML;
    requires Game;
    requires Save;
    requires Console;

    exports ui.main to javafx.graphics;
    exports ui.pantallas.principal;
    exports ui.pantallas.common;
    exports ui.pantallas.welcome;
    exports ui.pantallas.elegirPartida;
    exports ui.pantallas.juego;
    exports ui.pantallas.load;
    exports ui.pantallas.save;

    opens ui.pantallas.load;
    opens ui.pantallas.save;
    opens ui.pantallas.principal;
    opens ui.pantallas.elegirPartida;
    opens ui.pantallas.juego;
    opens ui.pantallas.welcome to javafx.fxml;
    opens ui.main;
    opens config;
    opens css;
    opens fxml;


}
