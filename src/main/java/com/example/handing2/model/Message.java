package com.example.handing2.model;

public class Message
{
  private String message;
  private Login login;
  public Message(Login login, String message)
  {
    this.login = login;
    this.message = message;
  }

  public String getMessage()
  {
    return message;
  }

  public Login getLogin()
  {
    return login;
  }
  public String toString()
  {
    return "Login: " + login + "; Message: " + message;
  }
}
