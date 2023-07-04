package com.example.dietatianjavafx.Controllers;

import com.example.dietatianjavafx.Models.CRUDFirebase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {


    @FXML
    private Button btnEkle;

    @FXML
    private Label lblMotivasyon;

    @FXML
    private TextField txtMotivasyon;

    private CRUDFirebase crudFirebase = new CRUDFirebase();

    @FXML
    void add(ActionEvent event) {
        if(crudFirebase.addMotivasyon(txtMotivasyon.getText().trim().toString())) System.out.println("Kayıt Başarılı");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblMotivasyon.setText("Günün Sözü: " + String.valueOf(crudFirebase.readMotivasyon()));
    }
}
