package com.example.dietatianjavafx.Controllers;

import com.example.dietatianjavafx.Models.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class DanisanlarController implements Initializable {

    @FXML
    private Button btnDanisanEkle;

    @FXML
    private Button btnIncele;

    @FXML
    private Button btnIncele1;

    @FXML
    private Button btnIncele11;

    @FXML
    private Label lblAdSoyad;

    @FXML
    private Label lblAdSoyad1;

    @FXML
    private Label lblAdSoyad11;

    @FXML
    private Label lblGuncelKilo;

    @FXML
    private Label lblGuncelKilo1;

    @FXML
    private Label lblGuncelKilo11;

    @FXML
    private TextField txtDanisanAra;

    @FXML
    private VBox vboxDanisanlar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addListeners();

    }
    private void addListeners(){
        btnDanisanEkle.setOnAction(event -> yeniDanisanEkle());
    }
    private void yeniDanisanEkle(){
        Model.getInstance().getViewFactory().showYeniDanisanEkle();
    }
    @FXML
    void btnIncele(ActionEvent event) {
        Model.getInstance().getViewFactory().showDanisanIncele();
    }

}
