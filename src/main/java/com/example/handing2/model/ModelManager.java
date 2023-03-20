package com.example.handing2.model;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface ModelManager
{
  ArrayList<Message> getMessages();
  void addPropertyChangeListener(String propertyName, PropertyChangeListener listener);
  void removePropertyChangeListener(String propertyName, PropertyChangeListener listener);
  void sendMessage(Message message);
}
