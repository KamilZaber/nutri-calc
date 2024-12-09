module com.solutions.sulmurz.nutricalc {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.solutions.sulmurz.nutricalc to javafx.fxml;
    opens com.solutions.sulmurz.nutricalc.controllers to javafx.fxml;
    exports com.solutions.sulmurz.nutricalc;
}