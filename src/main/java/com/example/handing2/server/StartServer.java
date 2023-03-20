package com.example.handing2.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StartServer
{
  public static void main(String[] args) throws IOException
  {
    ServerSocket socket = new ServerSocket(8080);
    UDPBroadcaster broadcaster = new UDPBroadcaster("230.0.0.0", 8888);
    while (true)
    {
      System.out.println("Server is ready for input in port 8080");
      Socket socket1 = socket.accept();
      ChatCommunicator communicator = new ChatCommunicator(socket1, broadcaster);
      Thread thread = new Thread(communicator);
      thread.start();
    }
  }
}
