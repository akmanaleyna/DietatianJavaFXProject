package com.example.dietatianjavafx.Controllers;

import com.example.dietatianjavafx.Models.CRUDFirebase;
import com.example.dietatianjavafx.Models.Danisan;
import com.example.dietatianjavafx.Models.Model;
import com.example.dietatianjavafx.Views.DanisanFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//package com.example.dietatianjavafx.Controllers;
public class DanisanlarController implements Initializable {

    @FXML
    private Button btnAra;
    @FXML
    private Button btnYenile;
    @FXML
    private Button btnDanisanEkle;
    @FXML
    private ListView<Danisan> danisanlarListview;
    @FXML
    private TextField txtDanisanAra;

    private static DanisanlarController instance;

    public static DanisanlarController getInstance() {
        if (instance == null) {
            instance = new DanisanlarController();
        }
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        danisanlarListview.setItems(Model.getInstance().getListDanisan());
        danisanlarListview.setCellFactory(param -> new DanisanFactory());
        addListeners();
        initAllDanisanList();
    }

    private void addListeners() {
        btnDanisanEkle.setOnAction(this::yeniDanisanEkle);
        Model.getInstance().updateListDanisan();
    }

    private void yeniDanisanEkle(ActionEvent event) {
        Model.getInstance().getViewFactory().showYeniDanisanEkle();
    }

    private void initAllDanisanList() {
        if (Model.getInstance().getListDanisan().isEmpty() || Model.getInstance().getListDanisan() == null) {
            Model.getInstance().readAllDanisan();
        }
    }
    @FXML
    void ara(ActionEvent event) {
        String arananAd = txtDanisanAra.getText().trim(); // Aranan adı al
        if (!arananAd.isEmpty()) {
            ObservableList<Danisan> filteredList = FXCollections.observableArrayList(); // Boş bir ObservableList oluştur
            for (Danisan danisan : Model.getInstance().getListDanisan()) {
                if (danisan.getAdiSoyadi().equalsIgnoreCase(arananAd)) { // Aranan ad ile eşleşen danışanları bul
                    filteredList.add(danisan);
                }
            }
            danisanlarListview.setItems(filteredList); // ListView'i filtrelenmiş listeyle güncelle
            danisanlarListview.refresh();
        } else {
            danisanlarListview.setItems(Model.getInstance().getListDanisan()); // Arama kriteri boşsa tüm danışanları göster
            danisanlarListview.refresh();
        }
    }

    @FXML
    void yenile(ActionEvent event) {
        txtDanisanAra.setText("");
        Model.getInstance().updateListDanisan();
        danisanlarListview.setItems(Model.getInstance().getListDanisan());
        danisanlarListview.refresh();
    }

}
