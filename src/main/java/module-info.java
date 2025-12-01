module miniproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens miniproject to javafx.fxml;
    opens miniproject.controller to javafx.fxml;
    opens miniproject.model to javafx.base;

    exports miniproject;
}
