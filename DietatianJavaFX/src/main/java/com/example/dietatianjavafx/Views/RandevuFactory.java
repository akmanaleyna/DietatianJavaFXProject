package com.example.dietatianjavafx.Views;

import com.example.dietatianjavafx.Controllers.RandevuIcerikKontroller;
import com.example.dietatianjavafx.Models.DateRandevu;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class RandevuFactory extends ListCell<DateRandevu> {
    @Override
    protected void updateItem(DateRandevu date, boolean empty) {
        super.updateItem(date, empty);
        if(empty){
            setText(null);
            setGraphic(null);
        }else{
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/RandevuIcerik.fxml"));
                RandevuIcerikKontroller controller = new RandevuIcerikKontroller(date);
                loader.setController(controller);
                setGraphic(loader.load());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

