package com.example.handing2.viewmodel;

import com.example.handing2.model.ModelManager;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;

public class InfoViewModel implements PropertyChangeListener
{
  private ModelManager model;
  private SimpleStringProperty infoText;
  private PropertyChangeSupport support;
  private ViewModelFactory viewModelFactory;

  public InfoViewModel(ModelManager model, ViewModelFactory viewModelFactory)
      throws RemoteException
  {
    this.model = model;
    this.viewModelFactory = viewModelFactory;
    infoText = new SimpleStringProperty();
    model.addPropertyChangeListener("count", this);
  }
  public void bindInfoText(StringProperty property)
  {
    infoText.bindBidirectional(property);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    if (evt.getPropertyName().equals("count"))
    {
      Platform.runLater(()->
      {
        int temp = (int) evt.getNewValue();
        infoText.set("Number of users: " + temp);
      });
    }
  }
}
