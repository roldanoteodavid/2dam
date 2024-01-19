module MiPrimerRest.DavidRoldan {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    requires lombok;
    requires org.apache.logging.log4j;

    requires retrofit2;
    requires retrofit2.converter.moshi;
    requires okhttp3;
    requires jakarta.inject;
    requires jakarta.cdi;
    requires io.vavr;
    requires com.squareup.moshi;

    exports ui.main to javafx.graphics;
    exports ui.pantallas.principal;
    exports ui.pantallas.common;
    exports dao.retrofit;
    exports common.config;
    exports ui.pantallas.characters;
    exports dao.impl;
    exports domain.usecases;
    exports domain.modelo.characters;
    exports domain.modelo.episodes;

    opens ui.pantallas.principal;
    opens ui.pantallas.characters;
    opens ui.main;
    opens fxml;
    opens common;
    opens config;
    opens domain.modelo.characters;
    opens domain.modelo.episodes;
}