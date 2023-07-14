package com.example.dietatianjavafx.Controllers;

import com.example.dietatianjavafx.Models.CRUDFirebase;
import com.example.dietatianjavafx.Models.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class DiyetYazmaControllers implements Initializable {

    @FXML
    private ChoiceBox<String> ComboKategori;

    @FXML
    private ChoiceBox<String> ComboOgun;

    @FXML
    private ChoiceBox<String> ComboYiyecek;

    @FXML
    private Button btnEkle;

    @FXML
    private Button btnGonder;

    @FXML
    private Button btnTemizle;

    @FXML
    private ChoiceBox<String> comboGun;

    @FXML
    private Label lblAdSoyad;

    @FXML
    private Label lblToplamKalori;

    @FXML
    private Label lblToplamKaloriListe;

    @FXML
    private ListView<String> listViewCarsamba;

    @FXML
    private ListView<String> listViewCuma;

    @FXML
    private ListView<String> listViewCumartesi;

    @FXML
    private ListView<String> listViewEkle;

    @FXML
    private ListView<String> listViewPazar;

    @FXML
    private ListView<String> listViewPazartesi;

    @FXML
    private ListView<String> listViewPersembe;

    @FXML
    private ListView<String> listViewSalı;

    @FXML
    private Spinner<Integer> spinnerAdet;

    private ObservableList<String> listekle = FXCollections.observableArrayList();

    private int kalori;

    ObservableList<String> daysOfWeek = FXCollections.observableArrayList(
            "Pazartesi",
            "Sali",
            "Carsamba",
            "Persembe",
            "Cuma",
            "Cumartesi",
            "Pazar"
    );
    ObservableList<String> mealtimes = FXCollections.observableArrayList(
            "Kahvaltı",
            "Araöğün1",
            "Öğle",
            "Araöğün2",
            "Akşam",
            "Araöğün3"

    );
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblAdSoyad.setText("Ad Soyad: " + Model.getInstance().getDanisan().getAdiSoyadi());
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10);
        valueFactory.setValue(1);
        spinnerAdet.setValueFactory(valueFactory);
        comboGun.setItems(daysOfWeek);
        ComboOgun.setItems(mealtimes);

        String danisanId = Model.getInstance().getDocumentIdbyFullName(Model.getInstance().getDanisan().getAdiSoyadi());

        Model.getInstance().updateListDanisan("Pazartesi", danisanId);
        listViewPazartesi.setItems(Model.getInstance().getPazartesiDiyetList());

        Model.getInstance().updateListDanisan("Sali", danisanId);
        listViewSalı.setItems(Model.getInstance().getSaliDiyetList());

        Model.getInstance().updateListDanisan("Carsamba", danisanId);
        listViewCarsamba.setItems(Model.getInstance().getCarsambaDiyetList());

        Model.getInstance().updateListDanisan("Persembe", danisanId);
        listViewPersembe.setItems(Model.getInstance().getPersembeDiyetList());

        Model.getInstance().updateListDanisan("Cuma", danisanId);
        listViewCuma.setItems(Model.getInstance().getCumaDiyetList());

        Model.getInstance().updateListDanisan("Cumartesi", danisanId);
        listViewCumartesi.setItems(Model.getInstance().getCumartesiDiyetList());

        Model.getInstance().updateListDanisan("Pazar", danisanId);
        listViewPazar.setItems(Model.getInstance().getPazarDiyetList());

    }


    @FXML
    void Ekle(ActionEvent event) {
        /*if(comboGun.getValue() != null && ComboOgun.getValue() != null && ComboKategori.getValue() != null && ComboYiyecek.getValue() != null){
            for(int i = 0 ; i < spinnerAdet.getValue(); i++) {
                listekle.add(String.valueOf(ComboYiyecek.getValue() + " " + Model.getInstance().printFieldValueInMap(ComboYiyecek.getValue(), ComboKategori.getValue())));
                kalori += Integer.parseInt(Model.getInstance().printFieldValueInMap(ComboYiyecek.getValue(), ComboKategori.getValue()));
                lblToplamKalori.setText(String.valueOf(kalori));
            }
            listViewEkle.setItems(listekle);
            listViewEkle.refresh();
        }*/
        if (comboGun.getValue() != null && ComboOgun.getValue() != null && ComboKategori.getValue() != null && ComboYiyecek.getValue() != null) {
            for (int i = 0; i < spinnerAdet.getValue(); i++) {
                String comboYiyecekValue = ComboYiyecek.getValue();
                String comboKategoriValue = ComboKategori.getValue();
                if (comboYiyecekValue != null && comboKategoriValue != null) {
                    // ComboYiyecek ve ComboKategori değerleri null değilse işlemleri gerçekleştir
                    listekle.add(String.valueOf(comboYiyecekValue + " " + Model.getInstance().printFieldValueInMap(comboYiyecekValue, comboKategoriValue)));
                    kalori += Integer.parseInt(Model.getInstance().printFieldValueInMap(comboYiyecekValue, comboKategoriValue));
                    lblToplamKalori.setText(String.valueOf(kalori));
                }
            }
            listViewEkle.setItems(listekle);
            listViewEkle.refresh();
        }
    }

    @FXML
    void gonder(ActionEvent event) {
        if(isBothChoiceBoxesSelected()){
            String[][] dizi = convertToTwoDimensionalArray(listekle);
            Model.getInstance().veriEkleDocument(comboGun.getValue().toString(),Model.getInstance().getDocumentIdbyFullName(Model.getInstance().getDanisan().getAdiSoyadi()),dizi,String.valueOf(sonrakiGun(gunAdi())),ogunAdi());
           System.out.println(comboGun.getValue().toString()+" "+Model.getInstance().getDocumentIdbyFullName(Model.getInstance().getDanisan().getAdiSoyadi())+" "+String.valueOf(sonrakiGun(gunAdi()))+" "+ogunAdi());
            Model.getInstance().updateListDanisan(comboGun.getValue().toString(), Model.getInstance().getDocumentIdbyFullName(Model.getInstance().getDanisan().getAdiSoyadi()));
            clearAll();
        }

    }

    @FXML
    void temizle(ActionEvent event) {
        clearAll();
    }
    @FXML
    void onComboOgunSelected(ActionEvent event) {
        clear();
        String selectedMeal = ComboOgun.getValue();
        System.out.println("Seçili Öğün: " + selectedMeal);
        Model.getInstance().updateMapNames(ComboOgun.getValue());
        ComboKategori.setItems(Model.getInstance().getMapname());
    }
    @FXML
    void onComboKategoriSelected(ActionEvent event){
        String selectedKategori = ComboKategori.getValue();
        System.out.println("Seçili Kategori: " + selectedKategori);
        Model.getInstance().updateFieldName(ComboKategori.getValue());
        ComboYiyecek.setItems(Model.getInstance().getfieldname());
    }
    public void clearAll(){
        kalori = 0;
        lblToplamKalori.setText(String.valueOf(kalori));
        comboGun.setValue(null);
        ComboKategori.setValue(null);
        ComboYiyecek.setValue(null);
        ComboOgun.setValue(null);

        String[][] dizi = convertToTwoDimensionalArray(listekle);

        for (int i = 0; i < dizi.length; i++) {
            for (int j = 0; j < dizi[i].length; j++) {
                System.out.print(dizi[i][j] + " ");
            }
            System.out.println();
        }

        listekle.clear();
        listViewEkle.refresh();
    }
    public void clear(){
        kalori = 0;
        lblToplamKalori.setText(String.valueOf(kalori));

        String[][] dizi = convertToTwoDimensionalArray(listekle);

        for (int i = 0; i < dizi.length; i++) {
            for (int j = 0; j < dizi[i].length; j++) {
                System.out.print(dizi[i][j] + " ");
            }
            System.out.println();
        }

        listekle.clear();
        listViewEkle.refresh();
    }
    public static String[][] convertToTwoDimensionalArray(ObservableList<String> list) {
        Map<String, Integer> itemCounts = new HashMap<>();

        for (String item : list) {
            String[] tokens = item.split(" ");
            String itemName = tokens[0];
            int itemCount = itemCounts.getOrDefault(itemName, 0) + 1;
            itemCounts.put(itemName, itemCount);
        }

        String[][] dizi = new String[itemCounts.size()][2];
        int index = 0;

        for (Map.Entry<String, Integer> entry : itemCounts.entrySet()) {
            String itemName = entry.getKey();
            int itemCount = entry.getValue();
            dizi[index][0] = itemName + "x" + itemCount;
            dizi[index][1] = list.get(index).split(" ")[1];
            index++;
        }

        return dizi;
    }
    public static int sonrakiGun(String gun) {
        LocalDate simdikiTarih = LocalDate.now();
        DayOfWeek simdikiGun = simdikiTarih.getDayOfWeek();
        DayOfWeek hedefGun = DayOfWeek.valueOf(gun.toUpperCase());

        int gunFarki = hedefGun.getValue() - simdikiGun.getValue();
        if (gunFarki <= 0) {
            gunFarki += 7;
        }

        LocalDate sonrakiGun = simdikiTarih.plusDays(gunFarki);
        return sonrakiGun.getDayOfMonth();
    }

    public String gunAdi(){
        if(comboGun.getValue().equals("Pazartesi"))
            return "monday";
        else if (comboGun.getValue().equals("Sali"))
            return "tuesday";
        else if (comboGun.getValue().equals("Carsamba"))
            return "wednesday";
        else if (comboGun.getValue().equals("Persembe"))
            return "thursday";
        else if (comboGun.getValue().equals("Cuma"))
            return "frıday";
        else if (comboGun.getValue().equals("Cumartesi"))
            return "saturday";
        return "sunday";
    }
    public String ogunAdi(){
        if(ComboOgun.getValue().equals("Kahvaltı"))
            return "breakfast";
        else if (ComboOgun.getValue().equals("Araöğün1"))
            return "snack";
        else if (ComboOgun.getValue().equals("Öğle"))
            return "lunch";
        else if (ComboOgun.getValue().equals("Araöğün2"))
            return "snack2";
        else if (ComboOgun.getValue().equals("Akşam"))
            return "dinner";
        return "snack3";
    }

    private boolean isBothChoiceBoxesSelected() {
        return comboGun.getValue() != null && ComboOgun.getValue() != null;
    }
}
