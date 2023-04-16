package com.example.handing2.model;


import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;

public class Chat implements Serializable
{
  private ArrayList<Message> conversation;
  private ArrayList<Login> users;
  private static Chat instance;
  private Chat()
  {
    conversation = new ArrayList<>();
    users = new ArrayList<>();
  }
  public synchronized ArrayList<Message> getMessages()
  {
    return conversation;
  }
  public synchronized void addMessage(Message message)
  {
    conversation.add(message);
  }
  public synchronized void addUser(Login user)
  {
    users.add(user);
  }
  public synchronized ArrayList<Login> getUsers()
  {
    return users;
  }
  public static synchronized Chat getInstance()
  {
    if (instance == null)
    {
      instance = new Chat();
    }
    return instance;
  }
}
