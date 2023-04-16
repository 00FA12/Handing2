package com.example.handing2.server;

import com.example.handing2.Client.ChatClient;
import com.example.handing2.Client.ChatClientImplementation;
import com.example.handing2.log.FileLog;
import com.example.handing2.model.Chat;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class StartServer
{
  public static void main(String[] args) throws RemoteException, IOException, AlreadyBoundException
  {
    Chat data = Chat.getInstance();
    FileLog log = FileLog.getInstance();
    Registry registry = LocateRegistry.createRegistry(8080);
    ChatClient serverModel = new ChatClientImplementation(data);
    Remote remote = UnicastRemoteObject.exportObject(serverModel, 8080);
    registry.bind("ServerModel", remote);
    System.out.println("Server is running.");
  }
}
