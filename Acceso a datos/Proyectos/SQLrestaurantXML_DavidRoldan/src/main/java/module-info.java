module javafx {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires lombok;
    requires jakarta.xml.bind;
    requires org.apache.logging.log4j;
    requires jakarta.inject;
    requires jakarta.cdi;
    requires io.vavr;
    requires io.reactivex.rxjava3;
    requires org.pdfsam.rxjavafx;
    requires java.logging;
    requires java.sql;

    exports ui.main to javafx.graphics;
    exports ui.screens.principal;
    exports ui.screens.login;
    exports ui.screens.common;
    exports ui.screens.customers;
    exports ui.screens.orders;
    exports model;
    exports dao.impl;
    exports ui.screens.welcome;
    exports model.errors;

    opens ui.screens.login;
    opens ui.screens.principal;
    opens ui.screens.customers;
    opens ui.main;
    opens css;
    opens fxml;
    opens services;
    opens common;
    opens services.impl;
    opens config;
    opens configFiles;
    opens model to jakarta.xml.bind;
    exports dao.impl.staticlists;
    exports dao.impl.files;
    exports model.xml;
    opens model.xml to jakarta.xml.bind;
}
