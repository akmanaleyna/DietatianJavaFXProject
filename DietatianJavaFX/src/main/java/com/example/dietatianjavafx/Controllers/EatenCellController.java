package com.example.dietatianjavafx.Controllers;

import com.example.dietatianjavafx.Models.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class EatenCellController implements Initializable {
    @FXML
    private Button buttonAyrinti;

    @FXML
    private Label lblAdSoyad;

    private String string;

    public EatenCellController(String string) {
        this.string = string;
    }

    @FXML
    void click(ActionEvent event) {
        Model.getInstance().setIsim(lblAdSoyad.getText());
        Model.getInstance().getViewFactory().showDiyetAyrinti();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblAdSoyad.setText(string);
    }
}
