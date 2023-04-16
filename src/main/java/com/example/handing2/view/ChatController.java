package com.example.handing2.view;

import com.example.handing2.model.Message;
import com.example.handing2.viewmodel.ChatViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.rmi.RemoteException;

public class ChatController
{
  @FXML private ListView<Message> messageListView;
  @FXML private TextArea text;
  private Region root;
  private ViewHandler viewHandler;
  private ChatViewModel chatViewModel;
  public void init(ViewHandler viewHandler, ChatViewModel chatViewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.chatViewModel = chatViewModel;
    this.root = root;
    chatViewModel.bindConversationList(messageListView.itemsProperty());
    chatViewModel.bindCurrentMessage(text.textProperty());
    text.requestFocus();
  }
  @FXML public void onSend() throws RemoteException
  {
    chatViewModel.send();
    text.setText("");
  }
  @FXML public void onInfo() throws IOException
  {
    chatViewModel.onInfo();
    viewHandler.openView(ViewFactory.INFO_VIEW);
  }
  public Region getRoot()
  {
    return root;
  }
}
