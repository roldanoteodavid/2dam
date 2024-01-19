module javafx11.multipantalla {


    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    requires lombok;
    requires org.apache.logging.log4j;


    requires jakarta.inject;
    requires jakarta.cdi;
    requires io.vavr;




    exports ui.main to javafx.graphics;
    exports ui.pantallas.principal;
    exports ui.pantallas.login;
    exports ui.pantallas.common;
    exports ui.pantallas.bienvenida;

    opens ui.pantallas.login to  javafx.fxml;
    opens ui.pantallas.principal;
    opens ui.main;
    opens css;
    opens fxml;


}
