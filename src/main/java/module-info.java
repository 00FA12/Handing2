module com.example.handing2 {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.desktop;
  requires com.google.gson;

  opens com.example.handing2 to javafx.fxml;
  opens com.example.handing2.model to com.google.gson;
  exports com.example.handing2;
}