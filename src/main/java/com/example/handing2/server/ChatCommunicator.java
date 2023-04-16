//package com.example.handing2.server;
//
//import com.example.handing2.log.FileLog;
//import com.example.handing2.model.Chat;
//import com.example.handing2.model.Login;
//import com.example.handing2.model.Message;
//import com.google.gson.Gson;
//
//import java.io.*;
//import java.net.Socket;
//
//public class ChatCommunicator implements Runnable
//{
//
//  private final Chat chat;
//  private final Gson gson;
//  private FileLog fileLog;
//
//  public ChatCommunicator(Chat chat, FileLog fileLog)
//  {
//
//    this.chat = chat;
//    gson = new Gson();
//    this.fileLog = fileLog;
//  }
//
//  private void communicate() throws IOException
//  {
//    try
//    {
//      InputStream inputStream = socket.getInputStream();
//      OutputStream outputStream = socket.getOutputStream();
//      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//      PrintWriter writer = new PrintWriter(outputStream, true);
//
//      loop:
//      while (true)
//      {
//        String request = reader.readLine();
//        switch (request)
//        {
//          case "send" ->
//          {
//            writer.println("giveMe");
//
//          }
//          case "send me the user list size" -> {
//            writer.println(chat.getUsers().size());
//          }
//          case "toma" ->
//          {
//            String message = reader.readLine();
//            Message message1 = gson.fromJson(message, Message.class);
//            if (message != null)
//            {
//              fileLog.log(message1.getLogin().getUsername() + " wrote: " + message1.getMessage());
//              chat.addMessage(message1);
//              writer.println("done");
//              broadcaster.broadcast(message);
//            }
//          }
//          case "login" ->
//          {
//            String login = reader.readLine();
//            Login login1 = gson.fromJson(login, Login.class);
//            if (chat.getUsers().contains(login1))
//            {
//              writer.println("loginSuccessful");
//              if(reader.readLine().equals("send me the list of messages"))
//                writer.println(gson.toJson(chat.getMessages()));
//              fileLog.log(login1.getUsername() + " logged in the system.");
//
//            }
//          }
//          case "register" ->
//          {
//            String login = reader.readLine();
//            Login login1 = gson.fromJson(login, Login.class);
//            if (!chat.getUsers().contains(login1))
//            {
//              chat.addUser(login1);
//              writer.println("registrationSuccessful");
//              if(reader.readLine().equals("send me the list of messages"))
//                writer.println(gson.toJson(chat.getMessages()));
//              fileLog.log(login1.getUsername() + " registered in the system.");
//
//            }
//            else {
//              writer.println("registration is unsuccessful");
//            }
//          }
//          case "close" ->
//          {
//            break loop;
//          }
//        }
//      }
//    }
//    catch (IOException e)
//    {
//      e.printStackTrace();
//    }
//    finally
//    {
//      synchronized (broadcaster)
//      {
//      }
//      socket.close();
//    }
//  }
//
//  @Override public void run()
//  {
//    try
//    {
//      communicate();
//    }
//    catch (IOException e)
//    {
//      e.printStackTrace();
//    }
//  }
//}