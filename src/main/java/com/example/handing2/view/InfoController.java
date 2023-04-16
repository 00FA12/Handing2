package com.example.handing2.view;

import com.example.handing2.viewmodel.ChatViewModel;
import com.example.handing2.viewmodel.InfoViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class InfoController
{
  @FXML private Label infoText;
  private Region root;
  private ViewHandler viewHandler;
  private InfoViewModel infoViewModel;

  public void init(ViewHandler viewHandler, InfoViewModel infoViewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.infoViewModel = infoViewModel;
    this.root = root;
    infoViewModel.bindInfoText(infoText.textProperty());
  }

  public Region getRoot()
  {
    return root;
  }

  public void onAccept()
  {
    viewHandler.openView(ViewFactory.CHAT_VIEW);
  }
}
