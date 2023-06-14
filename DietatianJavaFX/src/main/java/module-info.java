module com.example.dietatianjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires AnimateFX;
    requires com.google.auth.oauth2;
    requires com.google.auth;
    requires google.cloud.firestore;
    requires firebase.admin;

    opens com.example.dietatianjavafx to javafx.fxml;
    opens com.example.dietatianjavafx.Controllers to javafx.fxml;
    exports com.example.dietatianjavafx.Models;
    exports com.example.dietatianjavafx;

}

