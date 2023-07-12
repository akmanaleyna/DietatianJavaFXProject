package com.example.dietatianjavafx.Controllers;

import com.example.dietatianjavafx.Models.CRUDFirebase;
import com.example.dietatianjavafx.Models.Danisan;
import com.example.dietatianjavafx.Models.Model;
import com.example.dietatianjavafx.Models.Tarif;
import com.example.dietatianjavafx.Views.DanisanFactory;
import com.example.dietatianjavafx.Views.TarifFactory;
import javafx.event.ActionEvent;
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
    private ListView<Tarif> tarifListview;

    @FXML
    private TextField txtBaslik;

    @FXML
    private TextField txtKalori;

    @FXML
    private TextField txtTarif;

    @FXML
    private TextField txtTarifAra;

    @FXML
    private ImageView ımgViewTarif;
    private CRUDFirebase crudFirebase = new CRUDFirebase();
   private Model model;

   private static SaglikliTarifler instance;

   public static SaglikliTarifler getInstance(){
       if (instance == null){
           instance = new SaglikliTarifler();
       }
       return instance;
   }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = Model.getInstance();
        tarifListview.setItems(model.getListTarif());
        tarifListview.setCellFactory(param -> new TarifFactory());
        addListeners();
        initAllTarifList();
    }
     private void initAllTarifList(){
       if(model.getListTarif().isEmpty() || model.getListTarif() == null){
           model.readAllTarif();
       }
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
    @FXML
    void add(ActionEvent event) {
        Tarif tarif = new Tarif(txtBaslik.getText().trim().toString(),TxtIcerik.getText().trim().toString(),txtTarif.getText().trim().toString(),txtKalori.getText().trim().toString(),"");
        if (crudFirebase.addTarif(tarif)) {
            System.out.println("Kayıt Başarılı!!!");
            Model.getInstance().updateListTarif();
            clear();
        }
    }
    public void clear(){
        txtBaslik.clear();
        TxtIcerik.clear();
        txtTarif.clear();
        txtKalori.clear();
        btnResimEkle.setText("");
    }

}
