package com.example.dietatianjavafx.Controllers;

import com.example.dietatianjavafx.Models.CRUDFirebase;
import com.example.dietatianjavafx.Models.DateRandevu;
import com.example.dietatianjavafx.Models.Model;
import com.example.dietatianjavafx.Views.EatenPageFactory;
import com.example.dietatianjavafx.Views.HomepageRandevuFactory;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {


    @FXML
    private ListView<DateRandevu> listviewRandevu;

    @FXML
    private Button btnEkle;

    @FXML
    private Label lblMotivasyon;

    @FXML
    private ListView<String> listVieweaten;

    @FXML
    private TextField txtMotivasyon;

    private CRUDFirebase crudFirebase = new CRUDFirebase();

    @FXML
    void add(ActionEvent event) {
        if(crudFirebase.addMotivasyon(txtMotivasyon.getText().trim().toString())){
            System.out.println("Kayıt Başarılı");
            lblMotivasyon.setText("");
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblMotivasyon.setText("Günün Sözü: " + String.valueOf(crudFirebase.readMotivasyon()));
        listviewRandevu.setItems(Model.getInstance().getListDateRandevu());
        listviewRandevu.setCellFactory(param -> new HomepageRandevuFactory());
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        int dayOfMonth = today.getDayOfMonth();
        Model.getInstance().updateListRandevuByDay(String.valueOf(dayOfMonth),String.valueOf(month));
        listVieweaten.setItems(Model.getInstance().getListDiyetGonderenler());
        listVieweaten.setCellFactory(param -> new EatenPageFactory());
        Model.getInstance().updateDiyetGonderenler();
        listVieweaten.refresh();
    }

    private static String getTodayDay() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
        String day = now.format(formatter);

        if (day.length() == 1) {
            day = "0" + day;
        }

        return day;
    }
}

