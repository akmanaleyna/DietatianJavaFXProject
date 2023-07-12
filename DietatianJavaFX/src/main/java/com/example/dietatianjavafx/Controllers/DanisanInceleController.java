package com.example.dietatianjavafx.Controllers;

import com.example.dietatianjavafx.Models.CRUDFirebase;
import com.example.dietatianjavafx.Models.Danisan;
import com.example.dietatianjavafx.Models.KiloTakip;
import com.example.dietatianjavafx.Models.Model;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

//package com.example.dietatianjavafx.Controllers;
public class DanisanInceleController implements Initializable {

    @FXML
    private Button btnDuzenle;

    @FXML
    private Button btnKaydet;

    @FXML
    private Button btnListe;

    @FXML
    private Label lblEkBilgiler;

    @FXML
    private Label lblIsim;

    @FXML
    private Label lblKalori;

    @FXML
    private Label lblSuMiktari;

    @FXML
    private TableView<KiloTakip> tblView;

    @FXML
    private TableColumn<KiloTakip, String> kiloColumn;

    @FXML
    private TableColumn<KiloTakip, String> tarihColumn;

    @FXML
    private TextField txtFieldKilo;

    @FXML
    private TextField txtFieldSaat;

    @FXML
    private TextField txtFieldTarih;
    @FXML
    private TextArea textAreaNot;

    @FXML
    private DatePicker datePickerTarih;


    private Danisan danisan;

    @FXML
    void openListe(ActionEvent event) {
        Model.getInstance().setDanisan(danisan);
        Model.getInstance().getViewFactory().showDiyetYazma();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
            btnDuzenle.setDisable(true);
            danisan = Model.getInstance().getDanisan();
            if(danisan != null){
                lblIsim.setText("Adı Soyadı: " + danisan.getAdiSoyadi());
                if (danisan.getCinsiyet().equals("Kadin") || danisan.getCinsiyet().equals("Kadın")) {
                    double kalori = (665 + (9.56 * Double.parseDouble(danisan.getKilo())) + (1.85 * Double.parseDouble(danisan.getBoy())) - (4.68 * Double.parseDouble(danisan.getYas()))) * 1.375;
                    lblKalori.setText("Yaklaşık Kalori ~ " + (int) kalori);
                } else {
                    double kalori = (66 + (13.75 * Double.parseDouble(danisan.getKilo())) + (5 * Double.parseDouble(danisan.getBoy())) - (6.75 * Double.parseDouble(danisan.getYas()))) * 1.375;
                    lblKalori.setText("Yaklaşık Kalori ~ " + (int) kalori);
                }
                lblSuMiktari.setText("Günlük Su Miktarı ~ " + (int) (Double.parseDouble(danisan.getKilo()) * 30) + "ml");
                if(danisan.getHastaliklar() != "" || danisan.getNot() != "" )
                    textAreaNot.setText(danisan.getHastaliklar() + danisan.getNot());
                Model.getInstance().updateListKiloTakip(danisan);
                tblView.setItems(Model.getInstance().getListKiloTakip());
                kiloColumn.setCellValueFactory(new PropertyValueFactory<>("kilo"));
                tarihColumn.setCellValueFactory(new PropertyValueFactory<>("tarih"));

            } else
            System.out.println("Danışan bulunamadı.");

        //kiloColumn.setCellValueFactory(new PropertyValueFactory<>("kilo"));
        //tarihColumn.setCellValueFactory(new PropertyValueFactory<>("tarih"));

    }


    @FXML
    private void handleTableClick(MouseEvent event) {
        if (event.getClickCount() == 1) {
            KiloTakip selectedKiloTakip = tblView.getSelectionModel().getSelectedItem();
            if (selectedKiloTakip != null) {
                String tarih = selectedKiloTakip.getTarih();
                String kilo = selectedKiloTakip.getKilo();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                LocalDate parsedDate = LocalDate.parse(tarih, formatter);

                datePickerTarih.setValue(parsedDate);
                txtFieldKilo.setText(kilo);
                datePickerTarih.setDisable(true);
                btnKaydet.setDisable(true);
                btnDuzenle.setDisable(false);
            }
        }
    }

    @FXML
    void add(ActionEvent event) {
        if(dolumu()){
            String kilo = txtFieldKilo.getText();
            LocalDate tarih = datePickerTarih.getValue();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            String formattedTarih = tarih.format(formatter);

            Model model = Model.getInstance();
            String documentId = model.getDocumentIdbyFullName(danisan.getAdiSoyadi());
            model.addWeightTracking(documentId, new KiloTakip(formattedTarih, kilo));

            LocalDate today = LocalDate.now();
            if (tarih.isEqual(today) || tarih.isAfter(today)) {
                danisan.setKilo(txtFieldKilo.getText());
                Model.getInstance().updateDanisanWeight(danisan);

                Model.getInstance().updateListKiloTakip(danisan);
                tblView.setItems(Model.getInstance().getListKiloTakip());
                kiloColumn.setCellValueFactory(new PropertyValueFactory<>("kilo"));
                tarihColumn.setCellValueFactory(new PropertyValueFactory<>("tarih"));
            }
        }
        Model.getInstance().updateListDanisan();
    }


    @FXML
    void update(ActionEvent event) {
        if(dolumu()) {
            add(event);
            btnKaydet.setDisable(false);
            datePickerTarih.setDisable(false);
            btnDuzenle.setDisable(true);
            datePickerTarih.setValue(null);
            txtFieldKilo.setText(null);
            Model.getInstance().updateListDanisan();
        }
    }

    public Boolean dolumu() {
        if (datePickerTarih.getValue() == null || txtFieldKilo.getText() == null || txtFieldKilo.getText().isEmpty()) {
            return false;
        }
        return true;
    }

}