package com.example.handing2.Client;

import java.io.IOException;

public class StartClient
{
  public static void main(String[] args) throws IOException, InterruptedException
  {
    ChatClient client = new ChatClientImplementation("localhost", 8080, "230.0.0.0", 8888);
    client.login("test", "test1");
    client.send("test");
    client.addPropertyChangeListener(evt -> System.out.println(evt.getNewValue()));
    Thread.sleep(10_000);
    client.close();
  }
}
