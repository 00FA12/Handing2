package com.example.handing2.Client;

import java.io.IOException;
import java.beans.PropertyChangeListener;

public interface ChatClient
{
 void send(String message);
 void addPropertyChangeListener(PropertyChangeListener listener);
 void removePropertyChangeListener(PropertyChangeListener listener);
 void close() throws IOException;
 void login(String username, String password);
}
