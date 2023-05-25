package com.example.dietatianjavafx.Controllers;

import com.example.dietatianjavafx.Models.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class DanisanInceleController {

    @FXML
    private Button btnDuzenle;

    @FXML
    private Button btnKaydet;

    @FXML
    private Button btnListe;

    @FXML
    private Label lblEkBilgiler;

    @FXML
    private Label lblIsim;

    @FXML
    private Label lblKalori;

    @FXML
    private Label lblSuMiktari;

    @FXML
    private TableView<?> tblView;

    @FXML
    private TextField txtFieldKilo;

    @FXML
    private TextField txtFieldSaat;

    @FXML
    private TextField txtFieldTarih;

    @FXML
    void openListe(ActionEvent event) {
        Model.getInstance().getViewFactory().showDiyetYazma();
    }

}
