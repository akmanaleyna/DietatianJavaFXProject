package com.example.dietatianjavafx.Views;

import com.example.dietatianjavafx.Controllers.DanisanIcerikController;
import com.example.dietatianjavafx.Models.Danisan;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class DanisanFactory extends ListCell<Danisan> {
    @Override
    protected void updateItem(Danisan danisan, boolean empty) {
        super.updateItem(danisan, empty);
        if(empty){
            setText(null);
            setGraphic(null);
        }else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/DanisanIcerik.fxml"));
            DanisanIcerikController controller = new DanisanIcerikController(danisan);
            loader.setController(controller);
            setText(null);
            try{
                setGraphic(loader.load());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
