package com.example.handing2.Client;

import com.example.handing2.model.ChatModelManager;
import com.example.handing2.model.Login;
import com.example.handing2.model.ModelManager;
import com.example.handing2.view.ViewHandler;
import com.example.handing2.viewmodel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class StartClient2 extends Application
{
  @Override public void start(Stage primaryStage) throws Exception
  {
    ModelManager model = ChatModelManager.getInstance();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory);
    viewHandler.start(primaryStage);
  }
}
