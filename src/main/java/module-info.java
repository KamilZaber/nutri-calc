module com.solutions.sulmurz.nutricalc {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.solutions.sulmurz.nutricalc to javafx.fxml;
    exports com.solutions.sulmurz.nutricalc;
    exports com.solutions.sulmurz.nutricalc.views;
    opens com.solutions.sulmurz.nutricalc.views to javafx.fxml;
}