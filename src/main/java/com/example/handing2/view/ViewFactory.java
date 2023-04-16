package com.example.handing2.view;

import com.example.handing2.viewmodel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.io.IOException;

public class ViewFactory
{
  public static final String LOGIN_VIEW = "loginView";
  public static final String CHAT_VIEW = "chatView";
  public static final String INFO_VIEW = "infoView";
  private ViewHandler viewHandler;
  private ChatController chatController;
  private LoginController loginController;
  private InfoController infoController;
  private ViewModelFactory viewModelFactory;
  public ViewFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    this.viewModelFactory = viewModelFactory;
  }
  public Region loadChatView()
  {
    if (chatController == null)
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("Chat.fxml"));
      try
      {
        Region root = loader.load();
        chatController = loader.getController();
        chatController.init(viewHandler, viewModelFactory.getChatViewModel(), root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    return chatController.getRoot();
  }
  public Region loadLoginView()
  {
    if (loginController == null)
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("Login.fxml"));
      try
      {
        Region root = loader.load();
        loginController = loader.getController();
        loginController.init(viewHandler, viewModelFactory.getLoginViewModel(), root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
      return loginController.getRoot();
  }

  public Region loadInfoView()
  {
    if (infoController == null)
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("Info.fxml"));
      try
      {
        Region root = loader.load();
        infoController = loader.getController();
        infoController.init(viewHandler, viewModelFactory.getInfoViewModel(), root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    return infoController.getRoot();
  }
  public Region load(String id)
  {
    return switch (id)
        {
          case CHAT_VIEW -> loadChatView();
          case LOGIN_VIEW -> loadLoginView();
          case INFO_VIEW -> loadInfoView();
          default -> throw new IllegalArgumentException("Unknown view: " + id);
        };
  }
}
