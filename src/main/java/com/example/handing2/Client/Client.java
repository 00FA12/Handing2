package com.example.handing2.Client;

import com.example.handing2.model.Chat;
import com.example.handing2.model.ChatModelManager;
import com.example.handing2.model.Login;
import dk.via.remote.observer.RemotePropertyChangeListener;

import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client implements Runnable, ChatClient
{
    public final int port;
    private ChatClient chatClient;
    private MessageListener listener;
    private ChatModelManager model;
    private Chat chat;



    public Client(int port, ChatModelManager modelManager) throws RemoteException
    {
        this.port = port;
        model = modelManager;
        chat = Chat.getInstance();
    }

    @Override
    public void run()
    {
        try
        {
            Registry registry = LocateRegistry.getRegistry(port);
            chatClient = (ChatClient) registry.lookup("ServerModel");
            listener = new MessageListener(chatClient, model);
            chatClient.addPropertyChangeListener(listener);
        } catch (RemoteException | NotBoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public ChatClient getChatClient() throws RemoteException
    {
        return chatClient;
    }

    @Override public void send(String message) throws RemoteException
    {
        listener.send(message);
    }

    @Override public void addPropertyChangeListener(
        RemotePropertyChangeListener<Chat> listener) throws RemoteException
    {
        chatClient.addPropertyChangeListener(listener);
    }

    @Override public void removePropertyChangeListener(
        RemotePropertyChangeListener<Chat> listener) throws RemoteException
    {
        chatClient.removePropertyChangeListener(listener);
    }

    @Override public void firePropertyChange(String propertyName, Chat oldValue, Chat newValue) throws RemoteException
    {
        chatClient.firePropertyChange(propertyName, oldValue, newValue);
    }

    @Override public void close() throws RemoteException, IOException
    {

    }

    @Override public boolean login(Login user) throws RemoteException, IOException
    {
        return listener.login(user);
    }

    @Override public boolean register(Login user) throws RemoteException, IOException
    {
        System.out.println(user);
      return listener.register(user);
    }

    @Override public int getUserListSize() throws RemoteException, IOException
    {
        return listener.getUserListSize();
    }
    @Override
    public Chat getChat() throws RemoteException
    {
        return chatClient.getChat();
    }
    //todo be careful, maybe you need a method like getData()
}
