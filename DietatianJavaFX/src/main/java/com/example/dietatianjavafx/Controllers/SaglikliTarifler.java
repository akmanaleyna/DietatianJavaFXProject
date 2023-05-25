package com.example.dietatianjavafx.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SaglikliTarifler implements Initializable {

    @FXML
    private TextField TxtIcerik;

    @FXML
    private Button btnEkle;

    @FXML
    private Button btnResimEkle;

    @FXML
    private ListView<?> listViewSaglikliTarifler;

    @FXML
    private TextField txtBaslik;

    @FXML
    private TextField txtTarifAra;

    @FXML
    private TextField txtKalori;

    @FXML
    private TextField txtTarif;

    @FXML
    private ImageView ımgViewTarif;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addListeners();
    }
    private void addListeners(){
        btnResimEkle.setOnAction(event -> selectedImage());
    }
    public void selectedImage(){
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            ımgViewTarif.setImage(image);
            btnResimEkle.setText(selectedFile.toURI().toString());
        }

    }

}
