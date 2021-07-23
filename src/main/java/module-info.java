module GoogleLocationConverter {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.net.http;
    requires com.google.gson;
    requires tornadofx.controls;

    opens at.phisch.googlelocationconverter.ui to javafx.fxml;
    opens at.phisch.googlelocationconverter.json to com.google.gson;

    exports at.phisch.googlelocationconverter.ui;
    exports at.phisch.googlelocationconverter.json;

}