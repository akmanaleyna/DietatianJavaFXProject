package com.example.dietatianjavafx.Controllers;

import com.example.dietatianjavafx.Models.Danisan;
import com.example.dietatianjavafx.Models.DateRandevu;
import com.example.dietatianjavafx.Models.Model;
import com.example.dietatianjavafx.Views.RandevuFactory;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class RandevularımController implements Initializable {

    @FXML
    private ListView<DateRandevu> ListviewRandevu;

    @FXML
    private Button btnEkle;

    @FXML
    private Button btnYenile;

    @FXML
    private ChoiceBox<Danisan> danisanAdi;

    @FXML
    private DatePicker datePTarih;

    @FXML
    private DatePicker randevuAyarla;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ListviewRandevu.setItems(Model.getInstance().getListDateRandevu());
        ListviewRandevu.setCellFactory(param -> new RandevuFactory());
        initAllRandevuList();
        Model.getInstance().updateListRandevuFromToday();
        ListviewRandevu.refresh();

        initAllDanisanList();
        danisanAdi.setItems(Model.getInstance().getListDanisan()); // ChoiceBox'a listDanisan'ı ata
        danisanAdi.setConverter(new StringConverter<Danisan>() {
            @Override
            public String toString(Danisan danisan) {
                return danisan != null ? danisan.getAdiSoyadi() : "";
            }

            @Override
            public Danisan fromString(String string) {
                return null;
            }
        });
    }

    private void initAllRandevuList() {
        if (Model.getInstance().getListDateRandevu().isEmpty() || Model.getInstance().getListDateRandevu() == null) {
            Model.getInstance().readAllRandevu();
        }
    }

    private void initAllDanisanList() {
        if (Model.getInstance().getListDanisan().isEmpty() || Model.getInstance().getListDanisan() == null) {
            Model.getInstance().readAllDanisan();
        }
    }

    @FXML
    void changeDatePicker(ActionEvent event) {
        int[] dateParams = new int[3];
        parseDate(String.valueOf(datePTarih.getValue()), dateParams);
        System.out.println(String.valueOf(datePTarih.getValue()));
        Model.getInstance().updateListRandevuByDay(String.valueOf(dateParams[2]),String.valueOf(dateParams[1]));
        if (Model.getInstance().getListDateRandevu().isEmpty() || Model.getInstance().getListDateRandevu() == null) {
           System.out.println("Bu değer null");
        }
        ListviewRandevu.refresh();
    }

    @FXML
    void yenile(ActionEvent event) {
        Model.getInstance().updateListRandevuFromToday();
        ListviewRandevu.refresh();
        datePTarih.setValue(null);

    }

    @FXML
    void datePickerRandevuEkle(ActionEvent event) {

    }

    public void parseDate(String date, int[] dateParams) {
        String[] parts = date.split("-");
        if (parts.length == 3) {
            dateParams[0] = Integer.parseInt(parts[0]);
            dateParams[1] = Integer.parseInt(parts[1]);
            dateParams[2] = Integer.parseInt(parts[2]);
        }
    }


    @FXML
    void ekle(ActionEvent event) {
        if(danisanAdi.getValue() == null || randevuAyarla.getValue() == null){
            System.out.println("Lütfen bir değer giriniz");
        }else{
            int[] dateParams = new int[3];
            parseDate(String.valueOf(randevuAyarla.getValue()), dateParams);
            DateRandevu dateRandevu = new DateRandevu(String.valueOf(dateParams[2]),String.valueOf(dateParams[1]),"true",Model.getInstance().getDocumentIdbyFullName(danisanAdi.getValue().getAdiSoyadi()),"none","","Not assigned yet","","");
            if(Model.getInstance().addDateRandevu(dateRandevu)){
                System.out.println("Kayıt Başarılı");
                danisanAdi.setValue(null);
                randevuAyarla.setValue(null);
                Model.getInstance().updateListRandevuFromToday();
                ListviewRandevu.refresh();
            }
        }
    }
}