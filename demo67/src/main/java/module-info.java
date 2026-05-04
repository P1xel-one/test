module org.example.demo67 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.demo67 to javafx.fxml;
    exports org.example.demo67;
}