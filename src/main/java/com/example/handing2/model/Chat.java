package com.example.handing2.model;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class Chat
{
  private ArrayList<Message> conversation;
  public Chat()
  {
    conversation = new ArrayList<>();
  }
  public ArrayList<Message> getMessages()
  {
    return conversation;
  }
  public void addMessage(Message message)
  {
    conversation.add(message);
  }
}
