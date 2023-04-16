package com.example.handing2.Client;

import com.example.handing2.model.Chat;
import com.example.handing2.model.Login;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import com.example.handing2.model.Message;
import com.example.handing2.model.ModelManager;
import dk.via.remote.observer.RemotePropertyChangeEvent;
import dk.via.remote.observer.RemotePropertyChangeListener;

public class MessageListener extends UnicastRemoteObject implements RemotePropertyChangeListener<ArrayList<Message>>, Serializable, ChatClient
{
private ModelManager modelManager;
private ChatClient chatClient;
  public MessageListener(ChatClient chatClient, ModelManager model) throws IOException
  {
    this.chatClient = chatClient;
    this.modelManager =model;
  }

  @Override public void send(String message) throws RemoteException
  {
    chatClient.send(message);
  }

  @Override public void addPropertyChangeListener(RemotePropertyChangeListener<ArrayList<Message>> listener) throws RemoteException
  {
    chatClient.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(RemotePropertyChangeListener<ArrayList<Message>> listener) throws RemoteException
  {
    chatClient.removePropertyChangeListener(listener);
  }

  @Override public void firePropertyChange(String propertyName, ArrayList<Message> oldValue, ArrayList<Message> newValue) throws RemoteException
  {
    chatClient.firePropertyChange(propertyName, oldValue, newValue);
  }

  public void close() throws RemoteException, IOException
  {

  }

  @Override public boolean login(Login user) throws RemoteException, IOException
  {
    return chatClient.login(user);
  }

  @Override public boolean register(Login user) throws RemoteException, IOException
  {
    return chatClient.register(user);
  }

  @Override public int getUserListSize() throws RemoteException, IOException
  {
    return chatClient.getUserListSize();
  }

  @Override public void propertyChange(RemotePropertyChangeEvent<ArrayList<Message>> remotePropertyChangeEvent)
      throws RemoteException
  {
    ArrayList<Message> messages = remotePropertyChangeEvent.getNewValue();
    getChat().getUsers().clear();
    getChat().getUsers().addAll(chatClient.getChat().getUsers());
    getChat().getMessages().clear();
    getChat().getMessages().addAll(messages);
    if(remotePropertyChangeEvent.getPropertyName().equals("new message"))
    {
      var messages1 = remotePropertyChangeEvent.getNewValue();
      System.out.println("New message: " + messages1.get(messages.size() - 1));
//     modelManager.sendOthersMessage(messages.get(messages.size() - 1));
    }
  }
  public Chat getChat() throws RemoteException
  {
    return chatClient.getChat();
  }
}
