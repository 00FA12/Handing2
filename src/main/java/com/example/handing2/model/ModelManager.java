package com.example.handing2.model;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ModelManager
{
  ArrayList<Message> getMessages();
  void addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
      throws RemoteException;
  void removePropertyChangeListener(String propertyName, PropertyChangeListener listener)
      throws RemoteException;
  Message sendMessage(String message) throws RemoteException;
  void addUser(Login user) throws IOException;
  int getUserListSize() throws IOException;
  void login(Login user) throws RemoteException;
  void sendUsers() throws IOException;
}
