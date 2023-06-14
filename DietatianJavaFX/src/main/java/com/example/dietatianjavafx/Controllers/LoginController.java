

package com.example.dietatianjavafx.Controllers;

import com.example.dietatianjavafx.Models.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import animatefx.animation.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button bntBack;

    @FXML
    private Button btmParola;

    @FXML
    private Button btnNewPassword;

    @FXML
    private Button btnSingIn;

    @FXML
    private Label lblGiris;

    @FXML
    private Pane pnlForgotPassworld;

    @FXML
    private Pane pnlSingUp;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfNewEmail;

    @FXML
    private PasswordField tfPassword;

    @FXML
    void handleButtonAction(ActionEvent event) {
        if(event.getSource().equals(btnSingIn)){

        }
        if(event.getSource().equals(bntBack)){
            new FadeIn(pnlSingUp).play();
            pnlSingUp.toFront();
        }
        if(event.getSource().equals(btmParola)){
            new FadeIn(pnlForgotPassworld).play();
            pnlForgotPassworld.toFront();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnSingIn.setOnAction(event -> onLogin());
    }
    private void onLogin(){
        Model.getInstance().getViewFactory().closeStage((Stage) btmParola.getScene().getWindow());
        Model.getInstance().getViewFactory().showHomePage();
    }
}


