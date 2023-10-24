module com.example.thread {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.thread to javafx.fxml;
    exports com.example.thread;
}