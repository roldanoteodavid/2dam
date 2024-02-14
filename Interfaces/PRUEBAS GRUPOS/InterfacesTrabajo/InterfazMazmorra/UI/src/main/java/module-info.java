module UI {
    requires jakarta.cdi;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires jakarta.inject;
    requires Game;


    requires lombok;
    requires org.apache.logging.log4j;

    exports ui.pantallas.common;
    exports ui.pantallas.login;
    exports ui.pantallas.principal;
    opens ui.pantallas.bienvenida;
    exports ui.pantallas.dungeon;

    opens ui.main;

    // Other module-related declarations if any

//    opens ui.main to javafx.fxml;
    opens ui.pantallas.login;
    opens ui.pantallas.principal;
    opens ui.pantallas.inicio;
    exports ui.main;
    exports ui.pantallas.bienvenida;
    exports ui.pantallas.inicio;
}