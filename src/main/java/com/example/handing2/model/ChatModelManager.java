package com.example.handing2.model;

import com.example.handing2.Client.ChatClient;
import com.example.handing2.Client.ChatClientImplementation;
import com.example.handing2.Client.Client;
import dk.via.remote.observer.RemotePropertyChangeEvent;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ChatModelManager implements ModelManager
{
  private Client server;
  private Chat chat;
  private Login login;
  private PropertyChangeSupport support;
  public ChatModelManager() throws IOException
  {
    chat = Chat.getInstance();
    server = new Client(8080, this);
    server.run();
    support = new PropertyChangeSupport(this);


  }
  @Override public ArrayList<Message> getMessages()
  {
    return chat.getMessages();
  }

  @Override public void addPropertyChangeListener(String propertyName,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(propertyName, listener);
  }
  @Override public void removePropertyChangeListener(String propertyName,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(propertyName, listener);
  }

  @Override public Message sendMessage(String message) throws RemoteException
  {
    server.send(message);
    chat.addMessage(new Message(login, message));
    return chat.getMessages().get(chat.getMessages().size() - 1);
  }

  @Override public void addUser(Login user) throws RemoteException, IOException
  {
    if(server.register(user))
    {
      chat.addUser(user);
      login = user;
    }
  }

  @Override public int getUserListSize() throws RemoteException, IOException
  {
    return server.getUserListSize();
  }

  @Override public void login(Login user) throws RemoteException, RemoteException
  {
    try
    {
      if(server.login(user))
        login = user;
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public void sendUsers() throws RemoteException, IOException
  {
    support.firePropertyChange("count", null, getUserListSize());
  }
}
