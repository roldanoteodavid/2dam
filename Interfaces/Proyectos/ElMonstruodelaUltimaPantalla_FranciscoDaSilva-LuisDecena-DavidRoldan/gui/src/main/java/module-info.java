module gui {
    requires jakarta.cdi;
    requires javafx.graphics;
    requires jakarta.inject;
    requires javafx.fxml;
    requires javafx.controls;
    requires console;
    requires game;

    exports ui.main;
    exports ui.screen.home;
    exports ui.screen.principal;
    exports ui.screen.common;
    exports common;
    exports ui.screen.manageItems;

    opens ui.main;
    opens ui.screen.home;
    opens ui.screen.principal;
    opens ui.screen.common;
    opens common;
    exports ui.screen.dungeon;
    opens ui.screen.dungeon;
}