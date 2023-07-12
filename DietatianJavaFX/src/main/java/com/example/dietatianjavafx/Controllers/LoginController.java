

package com.example.dietatianjavafx.Controllers;

import com.example.dietatianjavafx.Models.CRUDFirebase;
import com.example.dietatianjavafx.Models.Dietician;
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
import java.util.concurrent.ExecutionException;

public class LoginController implements Initializable {

    @FXML
    private Button bntBack;

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

    private final CRUDFirebase crudFirebase = new CRUDFirebase();


    @FXML
    void handleButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnSingIn.setOnAction(event -> onLogin());
    }
    private void onLogin(){
        //if(islogin())
            Model.getInstance().getViewFactory().closeStage((Stage) btnSingIn.getScene().getWindow());
            Model.getInstance().getViewFactory().showHomePage();


    }
    /*
    private Boolean islogin(){
        Dietician dietician = new Dietician("","",tfEmail.getText().trim().toString());
        return crudFirebase.loginDietician(dietician);
    }
    */
}


