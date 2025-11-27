module miniproject {
    requires javafx.controls;
    requires javafx.fxml;

    opens miniproject to javafx.fxml;
    opens miniproject.controller to javafx.fxml;
    opens miniproject.model to javafx.base;

    exports miniproject;
}
