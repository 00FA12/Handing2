package com.example.handing2.model;

import java.io.Serializable;

public class Login implements Serializable
{
  private final String username;
  private final String password;
  public Login(String username, String password)
  {
    this.password = password;
    this.username = username;
  }

  public String getPassword()
  {
    return password;
  }

  public String getUsername()
  {
    return username;
  }
  public String toString()
  {
    return "Username: " + username + "; Password: " + password;
  }

  @Override public boolean equals(Object obj)
  {
    if (obj == null || obj.getClass() != getClass())
    {
      return false;
    }
    Login other = (Login) obj;
    return username.equals(other.username) && password.equals(other.password);
  }
}
