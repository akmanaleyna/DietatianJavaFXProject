package com.example.dietatianjavafx.Controllers;

import com.example.dietatianjavafx.Models.Danisan;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DanisanIcerikController implements Initializable {

    @FXML
    private Button btnIncele;

    @FXML
    private Label lblAdSoyad;

    @FXML
    private Label lblGuncelKilo;

    private final Danisan danisan;

    public DanisanIcerikController(Danisan danisan) {
        this.danisan = danisan;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
