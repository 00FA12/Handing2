package com.example.handing2.server;

import com.example.handing2.model.Login;
import com.example.handing2.model.Message;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ChatCommunicator implements Runnable
{
  private final Socket socket;
  private final UDPBroadcaster broadcaster;
  private ArrayList<Message> conversation;
  private ArrayList<Login> users;

  private final Gson gson;

  public ChatCommunicator(Socket socket, UDPBroadcaster broadcaster)
  {
    this.socket = socket;
    this.broadcaster = broadcaster;
    conversation = new ArrayList<>();
    users = new ArrayList<>();
    gson = new Gson();
  }

  private void communicate() throws IOException
  {
    try
    {
      InputStream inputStream = socket.getInputStream();
      OutputStream outputStream = socket.getOutputStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
      PrintWriter writer = new PrintWriter(outputStream, true);

      loop: while (true)
      {
        String request = reader.readLine();
        switch (request)
        {
          case "send" ->
          {
            writer.println("giveMe");
          }
          case "toma" ->
          {
            String message = reader.readLine();
            Message message1 = gson.fromJson(message, Message.class);
            conversation.add(message1);
            if (message != null)
            {
              writer.println("done");
            }
          }
          case "login" ->
          {
            String login = reader.readLine();
            Login login1 = gson.fromJson(login, Login.class);
            for (Login user : users)
            {
              if (login1.equals(user))
              {
                writer.println("loginSuccessful");
                break;
              }
            }
            users.add(login1);
            writer.println("registrationSuccessful");
          }
          case "close" ->
          {
            break loop;
          }
        }
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    finally
    {
      synchronized (broadcaster)
      {}
      socket.close();
    }
  }

  @Override public void run()
  {
    try
    {
      communicate();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}