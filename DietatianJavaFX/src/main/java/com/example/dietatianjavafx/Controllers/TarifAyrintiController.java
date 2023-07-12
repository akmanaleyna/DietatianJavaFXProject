package com.example.dietatianjavafx.Controllers;

import com.example.dietatianjavafx.Models.CRUDFirebase;
import com.example.dietatianjavafx.Models.Model;
import com.example.dietatianjavafx.Models.Tarif;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TarifAyrintiController implements Initializable {

        @FXML
        private TextArea textAreaAdimlar;

        @FXML
        private TextArea textAreaIcerik;

        @FXML
        private TextField txrTarifAdi;

        @FXML
        private Button btnGuncelle;


        @FXML
        private TextField txtKalori;

        private Tarif tarif;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
                if (txrTarifAdi.getText().isEmpty()) {
                        tarif = Model.getInstance().getTarif();
                        if (tarif != null) {
                                // Veriyi kullanarak işlemler yapabilirsiniz
                                tarifGetir(tarif);
                        }else System.out.println("Tarif Null");
                }
        }



        public void tarifGetir(Tarif tarif) {
                txrTarifAdi.setText(tarif.getTarifAdi().toString());
                txrTarifAdi.setDisable(true);
                txtKalori.setText(tarif.getTarifKalorisi().toString());
                textAreaAdimlar.setWrapText(true);
                textAreaAdimlar.setText(tarif.getTarifAdim().toString());
                textAreaIcerik.setWrapText(true);
                textAreaIcerik.setText(tarif.getTarifIcerik().toString());
        }
        @FXML
        public void guncelle(ActionEvent event) {
                tarif.setTarifIçerik(textAreaIcerik.getText().toString());
                tarif.setTarifAdim(textAreaAdimlar.getText().toString());
                tarif.setTarifKalorisi(txtKalori.getText().toString());
                Model.getInstance().updateTarif(tarif);
                Model.getInstance().updateListTarif();
        }
}