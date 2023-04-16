package com.example.handing2.viewmodel;

import com.example.handing2.model.ModelManager;

import java.rmi.RemoteException;

public class ViewModelFactory
{
  private ChatViewModel chatViewModel;
  private LoginViewModel loginViewModel;
  private InfoViewModel infoViewModel;

  public ViewModelFactory(ModelManager model) throws RemoteException
  {
    chatViewModel = new ChatViewModel(model, this);
    loginViewModel = new LoginViewModel(model, this);
    infoViewModel = new InfoViewModel(model, this);
  }

  public ChatViewModel getChatViewModel()
  {
    return chatViewModel;
  }

  public LoginViewModel getLoginViewModel()
  {
    return loginViewModel;
  }

  public InfoViewModel getInfoViewModel()
  {
    return infoViewModel;
  }
}
