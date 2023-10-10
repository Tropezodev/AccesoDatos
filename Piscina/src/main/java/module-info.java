module com.example.piscina {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.piscina to javafx.fxml;
    exports com.example.piscina;
}