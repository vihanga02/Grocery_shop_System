module org.example.myproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.myproject to javafx.fxml;
    exports org.example.myproject;
}