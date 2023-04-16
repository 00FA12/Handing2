module com.example.handing2 {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.desktop;
  requires javafx.graphics;
  requires com.google.gson;
  requires java.rmi;
  requires remoteobserver;

  opens com.example.handing2.model to com.google.gson;
  opens com.example.handing2.Client to javafx.graphics;
  opens com.example.handing2.view to javafx.fxml;
  exports com.example.handing2.view;
  exports com.example.handing2.Client to java.rmi;
}