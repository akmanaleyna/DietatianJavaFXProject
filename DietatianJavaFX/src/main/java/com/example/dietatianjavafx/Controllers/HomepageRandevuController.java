package com.example.dietatianjavafx.Controllers;

import com.example.dietatianjavafx.Models.Danisan;
import com.example.dietatianjavafx.Models.DateRandevu;
import com.example.dietatianjavafx.Models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HomepageRandevuController implements Initializable {


    @FXML
    private Label lblAdSoyad;

    private DateRandevu dateRandevu;

    public HomepageRandevuController(DateRandevu dateRandevu) {
        this.dateRandevu = dateRandevu;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblAdSoyad.setText(Model.getInstance().getNameUID(dateRandevu.getUid()));
    }
}
