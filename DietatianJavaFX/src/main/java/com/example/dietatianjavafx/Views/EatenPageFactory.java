package com.example.dietatianjavafx.Views;

import com.example.dietatianjavafx.Controllers.EatenCellController;
import com.example.dietatianjavafx.Controllers.TarifIcerikController;
import com.example.dietatianjavafx.Models.Tarif;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class EatenPageFactory extends ListCell<String> {
    @Override
    protected void updateItem(String string, boolean empty) {
        super.updateItem(string, empty);
        if(empty){
            setText(null);
            setGraphic(null);
        }else{
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/EatenCell.fxml"));
                EatenCellController controller = new EatenCellController(string);
                loader.setController(controller);
                setGraphic(loader.load());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
