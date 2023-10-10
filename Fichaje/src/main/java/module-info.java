module com.example.fichaje {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;

    opens com.example.fichaje to javafx.fxml;
    exports com.example.fichaje;
}