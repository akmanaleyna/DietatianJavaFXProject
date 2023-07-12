package com.example.dietatianjavafx.Controllers;

import com.example.dietatianjavafx.Models.DateRandevu;
import com.example.dietatianjavafx.Models.Model;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Calendar;
import java.util.Date;

import java.net.URL;
import java.util.ResourceBundle;

public class RandevuIcerikKontroller implements Initializable {
    @FXML
    private Button btnOnayla;

    @FXML
    private Button btnSil;

    @FXML
    private Label lblAdSoyad;

    @FXML
    private Label lblRandevu;
    private DateRandevu dateRandevu;
    public RandevuIcerikKontroller(DateRandevu dateRandevu) {
        this.dateRandevu = dateRandevu;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Şu anki tarihi al
        Date currentDate = new Date();

        // Calendar nesnesini oluştur
        Calendar calendar = Calendar.getInstance();

        // Calendar nesnesini şu anki tarihle ayarla
        calendar.setTime(currentDate);

        // Yılı al
        int year = calendar.get(Calendar.YEAR);
        if(dateRandevu.getIsConfirment().equals("true")) btnOnayla.setVisible(false);
        lblAdSoyad.setText(Model.getInstance().getNameUID(dateRandevu.getUid()));
        lblRandevu.textProperty().bind(Bindings.concat(dateRandevu.firstDayProperty(),new SimpleStringProperty("."),dateRandevu.firstMonthProperty(),new SimpleStringProperty("."),new SimpleStringProperty(String.valueOf(year))));
    }
    @FXML
    void delete(ActionEvent event) {
        //Model.getInstance().getDocumentIdbyFullName(lblAdSoyad.getText())
        Model.getInstance().deleteRandevu(dateRandevu.getUid().toString(),dateRandevu.getFirstDay().toString(), dateRandevu.getFirstMonth()).toString();
        Model.getInstance().updateListRandevu();
    }

    @FXML
    void onay(ActionEvent event) {
        if(Model.getInstance().updateRandevu(dateRandevu)){
            Model.getInstance().updateListRandevu();
        }

    }


}
