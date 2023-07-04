module com.example.dietatianjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires AnimateFX;
    requires com.google.auth.oauth2;
    requires google.cloud.firestore;
    requires google.cloud.core;
    requires firebase.admin;
    requires com.google.auth;
    requires com.google.api.apicommon;
    requires org.slf4j;


    opens com.example.dietatianjavafx to javafx.fxml;
    opens com.example.dietatianjavafx.Controllers to javafx.fxml;
    exports com.example.dietatianjavafx.Models;
    exports com.example.dietatianjavafx;

}

