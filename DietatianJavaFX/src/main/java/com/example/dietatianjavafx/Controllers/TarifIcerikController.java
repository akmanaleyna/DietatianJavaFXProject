package com.example.dietatianjavafx.Controllers;

import com.example.dietatianjavafx.Models.CRUDFirebase;
import com.example.dietatianjavafx.Models.Model;
import com.example.dietatianjavafx.Models.Tarif;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class TarifIcerikController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnİncele;

    @FXML
    private Label lblTarifAdi;

    private CRUDFirebase crudFirebase = new CRUDFirebase();


    private Tarif tarif;
    public TarifIcerikController(Tarif tarif) {
        this.tarif = tarif;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblTarifAdi.textProperty().bind(tarif.tarifAdiProperty());
    }

    @FXML
    void delete(ActionEvent event) {
        System.out.println("Buraya girmiyor");
        if(crudFirebase.deleteTarifByRecipeName(lblTarifAdi.getText().toString())){
            System.out.println("Tarif silindi");
            Model.getInstance().updateListTarif();
        }
    }

    @FXML
    void incele(ActionEvent event) {
        Model.getInstance().setTarif(crudFirebase.getTarifByRecipeName(lblTarifAdi.getText().toString()));
        Model.getInstance().getViewFactory().showTarifAyrinti();
        //Model.getInstance().setTarif(crudFirebase.getTarifByRecipeName(lblTarifAdi.getText().toString()));

    }
}
