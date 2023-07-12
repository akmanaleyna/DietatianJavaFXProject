
//DanisanIcerikController.java
package com.example.dietatianjavafx.Controllers;

import com.example.dietatianjavafx.Models.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

//package com.example.dietatianjavafx.Controllers;
public class DanisanIcerikController implements Initializable {

    @FXML
    private Button btnIncele, btnDelete;
    @FXML
    private Label lblAdiSoyadi;
    @FXML
    private Label lblGuncelKilo;

    private final Danisan danisan;

    public DanisanIcerikController(Danisan danisan) {
        this.danisan = danisan;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblAdiSoyadi.textProperty().bind(danisan.adiSoyadiProperty());
        lblGuncelKilo.textProperty().bind(Bindings.concat(new SimpleStringProperty("Güncel Kilo: "),danisan.kiloProperty()));
    }

    @FXML
    void add(ActionEvent event) {
        System.out.println(lblAdiSoyadi.getText());
        Model.getInstance().setDanisan(Model.getInstance().readDanisanByName(lblAdiSoyadi.getText()));
        Model.getInstance().getViewFactory().showDanisanIncele();
    }

    @FXML
    void delete(ActionEvent event) {
        Model.getInstance().deleteDanisan(lblAdiSoyadi.getText());
        Model.getInstance().updateListDanisan();

    }


}


