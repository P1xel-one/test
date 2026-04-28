module org.example.gg {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.gg to javafx.fxml;
    exports org.example.gg;
}