module com.solutions.sulmurz.nutricalc {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.google.gson;


    opens com.solutions.sulmurz.nutricalc to javafx.fxml;
    opens com.solutions.sulmurz.nutricalc.controllers to javafx.fxml;
    opens com.solutions.sulmurz.nutricalc.models to com.google.gson;
    exports com.solutions.sulmurz.nutricalc;
}