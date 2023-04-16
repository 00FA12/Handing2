package com.example.handing2.viewmodel;

import com.example.handing2.model.Message;
import com.example.handing2.model.ModelManager;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.rmi.RemoteException;

public class ChatViewModel implements PropertyChangeListener
{
  private final ModelManager model;
  private SimpleListProperty<Message> conversationList;
  private SimpleStringProperty currentMessage;
  private PropertyChangeSupport support;
  private ViewModelFactory viewModelFactory;

  public ChatViewModel(ModelManager model, ViewModelFactory viewModelFactory)
      throws RemoteException
  {
    this.model = model;
    this.viewModelFactory = viewModelFactory;
    currentMessage = new SimpleStringProperty();
    conversationList = new SimpleListProperty<>(FXCollections.observableArrayList());
    conversationList.addAll(model.getMessages());
    model.addPropertyChangeListener("receive message", this);
  }

  public void bindConversationList(ObjectProperty<ObservableList<Message>> property)
  {
    property.bind(conversationList);
  }

  public void bindCurrentMessage(StringProperty property)
  {
    property.bindBidirectional(currentMessage);
  }

  public void send() throws RemoteException
  {
    conversationList.add(model.sendMessage(currentMessage.get()));
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    if(evt.getPropertyName().equals("receive message"))
    {
      Platform.runLater(() ->
      {
        conversationList.add((Message) evt.getNewValue());
      });
    }
  }

  public void onInfo() throws IOException
  {
    model.sendUsers();
  }
}
