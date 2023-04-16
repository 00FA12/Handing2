package com.example.handing2.Client;

import com.example.handing2.model.Chat;
import com.example.handing2.model.Login;
import dk.via.remote.observer.RemotePropertyChangeListener;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClient extends Remote
{
 void send(String message) throws RemoteException;
 void addPropertyChangeListener(RemotePropertyChangeListener<Chat> listener) throws RemoteException;
 void removePropertyChangeListener(RemotePropertyChangeListener<Chat> listener) throws RemoteException;
 void firePropertyChange(String propertyName, Chat oldValue, Chat newValue)
     throws RemoteException;
 void close() throws RemoteException, IOException;
 boolean login(Login user) throws RemoteException, IOException;
 boolean register(Login user) throws RemoteException, IOException;
  int getUserListSize() throws RemoteException, IOException;
 Chat getChat() throws RemoteException;
}
