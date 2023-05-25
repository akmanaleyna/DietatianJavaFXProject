package com.example.dietatianjavafx.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;

public class DiyetYazmaControllers {

    @FXML
    private ChoiceBox<?> ComboOgun;

    @FXML
    private ChoiceBox<?> ComboYiyecek;

    @FXML
    private TextArea TextAreaToplam;

    @FXML
    private Button btnCikar;

    @FXML
    private Button btnEkle;

    @FXML
    private Button btnGonder;

    @FXML
    private Label lblToplamKalori;

    @FXML
    private Spinner<?> spinnerAdet;

}
