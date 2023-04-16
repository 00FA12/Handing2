package com.example.handing2.Client;

import com.example.handing2.model.Chat;
import com.example.handing2.model.Login;
import com.example.handing2.model.Message;
import dk.via.remote.observer.RemotePropertyChangeListener;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ChatClient extends Remote
{
 void send(String message) throws RemoteException;
 void addPropertyChangeListener(RemotePropertyChangeListener<ArrayList<Message>> listener) throws RemoteException;
 void removePropertyChangeListener(RemotePropertyChangeListener<ArrayList<Message>> listener) throws RemoteException;
 void firePropertyChange(String propertyName, ArrayList<Message> oldValue, ArrayList<Message> newValue) throws RemoteException;
 void close() throws RemoteException, IOException;
 boolean login(Login user) throws RemoteException, IOException;
 boolean register(Login user) throws RemoteException, IOException;
  int getUserListSize() throws RemoteException, IOException;
 Chat getChat() throws RemoteException;
}
