package com.example.handing2.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements Serializable
{
  private String message;
  private Login login;
  private static Date date;
  public Message(Login login, String message)
  {
    this.login = login;
    this.message = message;
    date = new Date();
  }
  public String getMessage()
  {
    return message;
  }

  public static String getDate()
  {
    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
    return format.format(date);
  }

  public Login getLogin()
  {
    return login;
  }
  public String toString()
  {
    return "User: " + getLogin().getUsername() + "; Message: " + message + " " + date;
  }
}