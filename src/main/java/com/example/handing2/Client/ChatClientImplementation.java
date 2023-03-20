package com.example.handing2.Client;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.net.Socket;

import com.example.handing2.model.Login;
import com.example.handing2.model.Message;
import com.google.gson.Gson;

public class ChatClientImplementation implements ChatClient
{
  private final Socket socket;
  private final PrintWriter writer;
  private final BufferedReader reader;
  private final Gson gson;
  private final PropertyChangeSupport support;
  private final MessageListener listener;
  private static Login login;
  public ChatClientImplementation(String host, int port, String groupAddress, int groupPort) throws IOException
  {
    socket = new Socket(host, port);
    writer = new PrintWriter(socket.getOutputStream(), true);
    InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
    reader = new BufferedReader(inputStreamReader);
    gson = new Gson();
    support = new PropertyChangeSupport(this);
    listener = new MessageListener(this, groupAddress, groupPort);
    Thread thread = new Thread(listener);
    thread.start();
  }

  @Override public void login(String username, String password)
  {
    writer.println("login");
    login = new Login(username, password);
    String loginJson = gson.toJson(login);
    writer.println(loginJson);
    try
    {
      if (reader.readLine().equals("loginSuccessful") || reader.readLine().equals("registrationSuccessful"))
      {
        System.out.println(loginJson);
      }
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }


  @Override public void send(String message)
  {
    String messageJson = "";
    writer.println("send");
    try
    {
      if (reader.readLine().equals("giveMe"))
      {
        writer.println("toma");
        messageJson = gson.toJson(new Message(login, message));
        writer.println(messageJson);
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    try
    {
      if (reader.readLine().equals("done"))
      {
        System.out.println(messageJson);
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }

  @Override public void close() throws IOException
  {
    writer.println("close");
    socket.close();
  }

  public void receiveBroadCast(String message) throws IOException
  {
//    String realMessage = reader.readLine();
    support.firePropertyChange("newMessage", null, message);
  }
}
