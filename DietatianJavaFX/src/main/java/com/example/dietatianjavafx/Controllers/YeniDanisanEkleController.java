package com.example.dietatianjavafx.Controllers;

import com.example.dietatianjavafx.Models.CRUDFirebase;
import com.example.dietatianjavafx.Models.Danisan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class YeniDanisanEkleController {

    @FXML
    private Button btnKaydet;

    @FXML
    private ToggleGroup cinsiyet;

    @FXML
    private TextField lblAdi;

    @FXML
    private TextField lblBoy;

    @FXML
    private TextField lblEmail;

    @FXML
    private TextField lblHedefKilo;

    @FXML
    private TextField lblKilo;

    @FXML
    private TextField lblSifre;

    @FXML
    private TextField lblYas;

    @FXML
    private RadioButton rdobCinsiyetErkek;

    @FXML
    private RadioButton rdobCinsiyetKadin;

    @FXML
    private TextArea textAreaHastalik;

    @FXML
    private TextArea textareaNot;

    private CRUDFirebase crudFirebase = new CRUDFirebase();

    @FXML
    void addDanisan(ActionEvent event){
        ekle();
        clear();
    }
    public void clear(){
        lblAdi.setText("");
        lblHedefKilo.setText("");
        lblYas.setText("");
        lblEmail.setText("");
        lblSifre.setText("");
        lblKilo.setText("");
        lblBoy.setText("");
        textareaNot.setText("");
        textAreaHastalik.setText("");
        rdobCinsiyetErkek.setSelected(true);
    }

    public void ekle(){
        String selectedCinsiyet ;
        if(rdobCinsiyetErkek.isSelected() == true)
            selectedCinsiyet = "Erkek";
        else
            selectedCinsiyet = "Kadın";
        Danisan danisan = new Danisan(lblAdi.getText().trim().toString(), lblHedefKilo.getText().trim().toString(), lblYas.getText().trim().toString(), lblEmail.getText().trim().toString(), lblSifre.getText().trim().toString(), selectedCinsiyet, lblBoy.getText().trim().toString(), lblKilo.getText().trim().toString(), textareaNot.getText().trim().toString(), textAreaHastalik.getText().trim().toString());
        if(crudFirebase.addDanisan(danisan)) System.out.println("Kayıt Başarılı");
    }

}
