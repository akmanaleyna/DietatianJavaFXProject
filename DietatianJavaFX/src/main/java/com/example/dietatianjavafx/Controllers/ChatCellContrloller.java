package com.example.dietatianjavafx.Controllers;
import com.example.dietatianjavafx.Models.Chat;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;


public class ChatCellContrloller implements Initializable {

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private Label lblIsim;

    @FXML
    private Label lblMetin;

    private Chat chat;

    public ChatCellContrloller(Chat chat) {
        this.chat = chat;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblIsim.setText(chat.getSender());
        lblMetin.setText(chat.getMessage());
        if (!chat.getSender().equals("Diyetisyen"))
            AnchorPane.setStyle("-fx-background-color: #E3D4FF;");
        else{
            lblIsim.setAlignment(Pos.CENTER_RIGHT);
            lblMetin.setAlignment(Pos.CENTER_RIGHT);
        }
    }
}