package com.example.dietatianjavafx.Views;

import com.example.dietatianjavafx.Controllers.TarifIcerikController;
import com.example.dietatianjavafx.Models.Tarif;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class TarifFactory extends ListCell<Tarif> {
    @Override
    protected void updateItem(Tarif tarif, boolean empty) {
        super.updateItem(tarif, empty);
        if(empty){
            setText(null);
            setGraphic(null);
        }else{
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/TarifIcerik.fxml"));
                TarifIcerikController controller = new TarifIcerikController(tarif);
                loader.setController(controller);
                setGraphic(loader.load());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
