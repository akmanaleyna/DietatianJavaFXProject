
//DanisanFactory.java
package com.example.dietatianjavafx.Views;

import com.example.dietatianjavafx.Controllers.DanisanIcerikController;
import com.example.dietatianjavafx.Models.Danisan;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class DanisanFactory extends ListCell<Danisan> {
    @Override
    protected void updateItem(Danisan danisan, boolean empty) {
        super.updateItem(danisan, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/DanisanIcerik.fxml"));
                DanisanIcerikController controller = new DanisanIcerikController(danisan);
                loader.setController(controller);
                setGraphic(loader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
///Fxml/DanisanIcerik.fxml


