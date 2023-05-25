package com.example.dietatianjavafx.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

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

}
