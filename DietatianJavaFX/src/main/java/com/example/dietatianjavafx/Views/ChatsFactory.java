package com.example.dietatianjavafx.Views;

import com.example.dietatianjavafx.Controllers.ChatCellContrloller;
import com.example.dietatianjavafx.Models.Chat;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class ChatsFactory  extends ListCell<Chat> {
    @Override
    protected void updateItem(Chat chat, boolean empty) {
        super.updateItem(chat, empty);
        if(empty){
            setText(null);
            setGraphic(null);
        }else{
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/ChatCell.fxml"));
                ChatCellContrloller controller = new ChatCellContrloller(chat);
                loader.setController(controller);
                setGraphic(loader.load());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
