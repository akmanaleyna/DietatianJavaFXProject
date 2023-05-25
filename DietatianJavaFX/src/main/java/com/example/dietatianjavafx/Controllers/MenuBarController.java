package com.example.dietatianjavafx.Controllers;

import com.example.dietatianjavafx.Models.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuBarController implements Initializable {

    @FXML
    private Button btnChat;

    @FXML
    private Button btnCikis;

    @FXML
    private Button btnDanisan;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnSaat;

    @FXML
    private Button btnSetting;

    @FXML
    private Button btnTarif;

    @FXML
    private Button btnVideoCall;

    @FXML
    void handleButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addListeners();
    }
    private void addListeners(){
        btnHome.setOnAction(event -> onHomePage());
        btnDanisan.setOnAction(event -> onDanisanlar());
        btnTarif.setOnAction(event -> onTarifler());
        btnSaat.setOnAction(event -> onRandevularim());
        btnCikis.setOnAction(event -> Model.getInstance().getViewFactory().closeStage((Stage) btnCikis.getScene().getWindow()));
        btnChat.setOnAction(event -> onChat());
        btnVideoCall.setOnAction(event -> onVideoCall());
        btnSetting.setOnAction(event -> onSetting());
    }
    private void onHomePage(){
        Model.getInstance().getViewFactory().getPageSelectedMenuItem().set("HomePage");
    }
    private void onDanisanlar(){

        Model.getInstance().getViewFactory().getPageSelectedMenuItem().set("Danisanlar");
    }
    private void onTarifler(){
        Model.getInstance().getViewFactory().getPageSelectedMenuItem().set("SaglikliTarifler");
    }
    private void onRandevularim(){
        Model.getInstance().getViewFactory().getPageSelectedMenuItem().set("Randevularim");
    }
    private void onChat(){
        Model.getInstance().getViewFactory().getPageSelectedMenuItem().set("Chat");
    }
    private void onVideoCall(){
        Model.getInstance().getViewFactory().getPageSelectedMenuItem().set("VideoCall");
    }
    private void onSetting(){
        Model.getInstance().getViewFactory().getPageSelectedMenuItem().set("Ayarlar");
    }

}
