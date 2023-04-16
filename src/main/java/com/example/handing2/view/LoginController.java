package com.example.handing2.view;

import com.example.handing2.model.Login;
import com.example.handing2.viewmodel.LoginViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import java.io.IOException;

public class LoginController
{
  @FXML private TextField user, password;
  @FXML private Label error;
  private Region root;
  private ViewHandler viewHandler;
  private LoginViewModel loginViewModel;

  public void init(ViewHandler viewHandler, LoginViewModel loginViewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.loginViewModel = loginViewModel;
    this.root = root;
    loginViewModel.bindUser(user.textProperty());
    loginViewModel.bindError(error.textProperty());
    loginViewModel.bindPassword(password.textProperty());
    password.requestFocus();
    user.requestFocus();
  }

  @FXML public void onLogin()
  {
    if (loginViewModel.login())
    {
      loginViewModel.login();
      viewHandler.openView(ViewFactory.CHAT_VIEW);
    }
  }

  @FXML public void onRegister() throws IOException
  {
    if (loginViewModel.register())
      viewHandler.openView(ViewFactory.CHAT_VIEW);
  }

  public Region getRoot()
  {
    return root;
  }
}
