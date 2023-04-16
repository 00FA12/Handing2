package com.example.handing2.Client;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.rmi.MarshalException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import com.example.handing2.model.Chat;
import com.example.handing2.model.Login;
import com.example.handing2.model.Message;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dk.via.remote.observer.RemotePropertyChangeListener;
import dk.via.remote.observer.RemotePropertyChangeSupport;

public class ChatClientImplementation implements ChatClient {

private final RemotePropertyChangeSupport<ArrayList<Message>> support;
private final Chat chat;
private static Login login;

  public ChatClientImplementation(Chat chat) throws RemoteException, IOException {
    this.support = new RemotePropertyChangeSupport<>();
    this.chat = chat;
  }

  @Override
  public boolean login(Login user) throws RemoteException, IOException
  {
    if (chat.getUsers().contains(user))
    {
      login = user;
      support.firePropertyChange("list of messages", null, chat.getMessages());
      return true;
    }
    else
    {
      throw new IOException("Username or Password are incorrect");
    }
  }

  @Override
  public boolean register(Login user) throws RemoteException, IOException {
    if (!chat.getUsers().contains(user))
    {
      login = user;
      support.firePropertyChange("list of messages", null, chat.getMessages());
      return true;
    }
    else throw new IOException("User has already been registered");
  }

  @Override
  public int getUserListSize() throws RemoteException, IOException {
    return chat.getUsers().size();
  }

  @Override public Chat getChat() throws RemoteException
  {
    return chat;
  }

  @Override
  public void send(String message) throws RemoteException
  {
    Message message1 = new Message(login, message);
    chat.addMessage(message1);
    support.firePropertyChange("new message", null, chat.getMessages());
  }

  @Override public void addPropertyChangeListener(RemotePropertyChangeListener<ArrayList<Message>> listener) throws RemoteException
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(RemotePropertyChangeListener<ArrayList<Message>> listener) throws RemoteException
  {
    support.removePropertyChangeListener(listener);
  }

  @Override public void firePropertyChange(String propertyName, ArrayList<Message> oldValue, ArrayList<Message> newValue) throws RemoteException
  {
    support.firePropertyChange(propertyName, oldValue, newValue);
  }

  @Override
  public void close() throws RemoteException, IOException {}

//  public void receiveBroadCast(String message) throws IOException {
//    ArrayList<Message> realMessage = gson.fromJson(message, Message.class);
//    System.out.println(realMessage);
//    support.firePropertyChange("receive message", null, realMessage);
//  }
}
