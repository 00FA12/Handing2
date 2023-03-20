package com.example.handing2.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ChatModelManager implements ModelManager
{
  private Chat chat;
  private PropertyChangeSupport support;
  public ChatModelManager()
  {
    chat = new Chat();
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

  @Override public void sendMessage(Message message)
  {
    chat.addMessage(message);
  }
}
