package com.example.dietatianjavafx.Controllers;

import com.example.dietatianjavafx.Models.CRUDFirebase;
import com.example.dietatianjavafx.Models.Dietician;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AyarlarControllers {

    @FXML
    private Button btnAdd;

    @FXML
    private TextField txtAdSoyad;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtSifre;

    private Dietician dietician;

    private CRUDFirebase crudFirebase = new CRUDFirebase();

    @FXML
    void add(ActionEvent event) {
        addDietician();
        clear();
    }

    private void addDietician(){
        dietician = new Dietician(txtAdSoyad.getText().trim().toString(),txtSifre.getText().trim().toString(),txtEmail.getText().trim().toString());
        if(crudFirebase.addDietician(dietician)) System.out.println("Kayıt Başarılı");

    }
    public void clear(){
        txtAdSoyad.setText("");
        txtEmail.setText("");
        txtSifre.setText("");
    }
}