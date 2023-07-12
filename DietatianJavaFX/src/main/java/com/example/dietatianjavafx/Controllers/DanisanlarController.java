package com.example.dietatianjavafx.Controllers;

import com.example.dietatianjavafx.Models.CRUDFirebase;
import com.example.dietatianjavafx.Models.Danisan;
import com.example.dietatianjavafx.Models.Model;
import com.example.dietatianjavafx.Views.DanisanFactory;
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

}
