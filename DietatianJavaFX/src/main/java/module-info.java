module com.example.dietatianjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires AnimateFX;

    opens com.example.dietatianjavafx to javafx.fxml;
    opens com.example.dietatianjavafx.Controllers to javafx.fxml;
    exports com.example.dietatianjavafx;
}