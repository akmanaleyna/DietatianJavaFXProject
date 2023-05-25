package com.example.dietatianjavafx.Controllers;

import com.example.dietatianjavafx.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class OpenAllPageController implements Initializable {

    public BorderPane allPage_parent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Model.getInstance().getViewFactory().getPageSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal){
                case "Danisanlar" -> allPage_parent.setCenter(Model.getInstance().getViewFactory().getDanisanlar());
                case "SaglikliTarifler" -> allPage_parent.setCenter(Model.getInstance().getViewFactory().getSaglikliTarifler());
                case "Randevularim" -> allPage_parent.setCenter(Model.getInstance().getViewFactory().getRandevularim());
                case "Chat" -> allPage_parent.setCenter(Model.getInstance().getViewFactory().getChat());
                case "VideoCall" -> allPage_parent.setCenter(Model.getInstance().getViewFactory().getVideoCall());
                case "Ayarlar" -> allPage_parent.setCenter(Model.getInstance().getViewFactory().getAyarlar());
                default -> allPage_parent.setCenter(Model.getInstance().getViewFactory().getDashboardView());
            }
        });
    }
}
