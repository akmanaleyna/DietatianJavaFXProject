package com.example.dietatianjavafx.Controllers;

import com.example.dietatianjavafx.Models.CRUDFirebase;
import com.example.dietatianjavafx.Models.Eaten;
import com.example.dietatianjavafx.Models.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class IkiListeyiKarsılastırmaController implements Initializable {

    @FXML
    private ListView<String> ListviewDiyetisyen;
    @FXML
    private Label lblAdSoyad;
    @FXML
    private ListView<String> listviewDanisan;
     ObservableList<String> yeni = FXCollections.observableArrayList();
    private CRUDFirebase crudFirebase= new CRUDFirebase();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Model.getInstance().updateEatenData();
        listviewDanisan.setItems(Model.getInstance().getEatenData());

        LocalDate today = LocalDate.now();

        lblAdSoyad.setText(Model.getInstance().getIsim());

        // Gün ismini almak
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE");
        String dayOfWeek = today.format(formatter);
        Model.getInstance().updateListDanisan(gunAdi(dayOfWeek), Model.getInstance().getDocumentIdbyFullName(lblAdSoyad.getText().toString()));
        if(dayOfWeek.equals("Pazartesi"))
            ListviewDiyetisyen.setItems(Model.getInstance().getPazartesiDiyetList());
        else if (dayOfWeek.equals("Salı"))
            ListviewDiyetisyen.setItems(Model.getInstance().getSaliDiyetList());
        else if (dayOfWeek.equals("Çarşamba"))
            ListviewDiyetisyen.setItems(Model.getInstance().getCarsambaDiyetList());
        else if (dayOfWeek.equals("Perşembe"))
            ListviewDiyetisyen.setItems(Model.getInstance().getPersembeDiyetList());
        else if (dayOfWeek.equals("Cuma"))
            ListviewDiyetisyen.setItems(Model.getInstance().getCumaDiyetList());
        else if (dayOfWeek.equals("Cumartesi"))
            ListviewDiyetisyen.setItems(Model.getInstance().getCumartesiDiyetList());
        else
            ListviewDiyetisyen.setItems(Model.getInstance().getPazarDiyetList());


    }
    public String gunAdi(String gun){
        if(gun.equals("Pazartesi"))
            return "Pazartesi";
        else if (gun.equals("Salı"))
            return "Sali";
        else if (gun.equals("Çarşamba"))
            return "Carsamba";
        else if (gun.equals("Perşembe"))
            return "Persembe";
        else if (gun.equals("Cuma"))
            return "Cuma";
        else if (gun.equals("Cumartesi"))
            return "Cumartesi";
        return "Pazar";
    }
}
