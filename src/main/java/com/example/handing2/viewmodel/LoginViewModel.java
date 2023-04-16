package com.example.handing2.viewmodel;

import com.example.handing2.model.Login;
import com.example.handing2.model.ModelManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.swing.*;
import java.io.IOException;

public class LoginViewModel
{
  private StringProperty user, password, error;
  private ModelManager model;
  private ViewModelFactory viewModelFactory;

  public LoginViewModel(ModelManager model, ViewModelFactory viewModelFactory)
  {
    this.model = model;
    this.viewModelFactory = viewModelFactory;
    user = new SimpleStringProperty();
    password = new SimpleStringProperty();
    error = new SimpleStringProperty("");
  }
  public void bindUser(StringProperty property)
  {
    property.bindBidirectional(user);
  }
  public void bindPassword(StringProperty property)
  {
    property.bindBidirectional(password);
  }
  public void bindError(StringProperty property)
  {
    property.bindBidirectional(error);
  }
  public void reset()
  {
    user.set("");
    password.set("");
  }
  public boolean login()
  {
    try
    {
      model.login(new Login(user.get(), password.get()));
    }
    catch (Exception e)
    {
      e.printStackTrace();
      error.set(e.getMessage());
      return false;
    }
    return true;
  }
  public boolean register() throws IOException
  {
    try
    {
      model.addUser(new Login(user.get(), password.get()));
    }
    catch (Exception e)
    {
      e.printStackTrace();
      error.set(e.getMessage());
      return false;
    }
    return true;
  }
}
