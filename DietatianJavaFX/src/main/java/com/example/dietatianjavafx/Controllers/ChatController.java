package com.example.dietatianjavafx.Controllers;

import com.example.dietatianjavafx.Models.CRUDFirebase;
import com.example.dietatianjavafx.Models.Chat;
import com.example.dietatianjavafx.Models.Model;
import com.example.dietatianjavafx.Views.ChatsFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;

public class ChatController implements Initializable {

        @FXML
        private Button btnGonder;

        @FXML
        private Button btnYenile;

        @FXML
        private ListView<Chat> listviewChat;

        @FXML
        private ListView<String> listviewIsim;

        @FXML
        private TextField listviewMessage;

        private Chat chat;

        private String selectedText;

        @FXML
        void gonder(ActionEvent event) {
            if(listviewMessage.getText()!= null || listviewMessage.getText()!=""){
                // Şu anki zamanı al
                Date now = new Date();
                // Saniyeyi almak için getTime metodu kullanılır
                long milliseconds = now.getTime();
                chat = new Chat(listviewMessage.getText(),"Diyetisyen",String.valueOf(milliseconds));
                chat.setMessage(listviewMessage.getText());
                chat.setSender("Diyetisyen");
                chat.setTime(String.valueOf(milliseconds));
                Model.getInstance().sendChatMessage(selectedText,chat);
                yenile(event);
                listviewMessage.clear();
            }
        }

        @FXML
        void yenile(ActionEvent event) {
            Model.getInstance().updateChatList(selectedText);
            listviewChat.setItems(Model.getInstance().getChatList());
            listviewChat.refresh();
            Model.getInstance().updateChatNames();
            listviewIsim.refresh();
        }
    private CRUDFirebase crudFirebase = new CRUDFirebase();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Model.getInstance().updateChatNames();
        listviewIsim.setItems(Model.getInstance().getChatNames());

        listviewIsim.setOnMouseClicked(event -> {
            selectedText = listviewIsim.getSelectionModel().getSelectedItem();
            System.out.println(selectedText);
            //crudFirebase.fetchChatsByChatName(selectedText);
            Model.getInstance().updateChatList(selectedText);
            listviewChat.setItems(Model.getInstance().getChatList());
            listviewChat.setCellFactory(param -> new ChatsFactory());
        });
    }
}
