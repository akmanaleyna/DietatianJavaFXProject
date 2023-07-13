
package com.example.dietatianjavafx.Models;

import com.example.dietatianjavafx.Views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Model {
    private static Model model;
    private ViewFactory viewFactory;
    private CRUDFirebase crudFirebase;
    private ObservableList<Danisan> listDanisan;
    private ObservableList<Tarif> listTarif;
    private ObservableList<DateRandevu> listRandevu;

    private ObservableList<KiloTakip> kiloTakipList ;
    private ObservableList<String> mapname;

    private ObservableList<String> fieldname;
    private ObservableList<String> pazartesiDiyetList;
    private ObservableList<String> saliDiyetList;
    private ObservableList<String> carsambaDiyetList;
    private ObservableList<String> persembeDiyetList;
    private ObservableList<String> cumaDiyetList;
    private ObservableList<String> cumartesiDiyetList;
    private ObservableList<String> pazarDiyetList;

    private ObservableList<String> chatNameList;

    private ObservableList<Chat> chatList;

    private ObservableList<String> diyetGonderenler;
    private Tarif tarif;

    private String isim;

    private Danisan danisan;

    private Model() {
        this.viewFactory = new ViewFactory();
        this.crudFirebase = new CRUDFirebase();
        this.listDanisan = FXCollections.observableArrayList();
        this.listTarif = FXCollections.observableArrayList();
        this.listRandevu = FXCollections.observableArrayList();
        this.kiloTakipList = FXCollections.observableArrayList();
        this.mapname = FXCollections.observableArrayList();
        this.fieldname = FXCollections.observableArrayList();
        this.pazartesiDiyetList = FXCollections.observableArrayList();
        this.saliDiyetList = FXCollections.observableArrayList();
        this.carsambaDiyetList = FXCollections.observableArrayList();
        this.persembeDiyetList = FXCollections.observableArrayList();
        this.cumaDiyetList = FXCollections.observableArrayList();
        this.cumartesiDiyetList = FXCollections.observableArrayList();
        this.pazarDiyetList = FXCollections.observableArrayList();
        this.chatNameList = FXCollections.observableArrayList();
        this.chatList = FXCollections.observableArrayList();
        this.diyetGonderenler = FXCollections.observableArrayList();
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public ObservableList<Danisan> getListDanisan() {
        return listDanisan;
    }

    public boolean readAllDanisan() {
        return crudFirebase.readAllDanisan(listDanisan);
    }

    public void updateListDanisan() {
        listDanisan.clear();
        crudFirebase.readAllDanisan(listDanisan);
    }

    public ObservableList<KiloTakip> getListKiloTakip() {
        return kiloTakipList;
    }

    public boolean readAllKiloTakip(Danisan danisan) {
        return crudFirebase.getWeightTrackingByDocumentId(getDocumentIdbyFullName(danisan.getAdiSoyadi()),kiloTakipList);
    }

    public void updateListKiloTakip(Danisan danisan) {
        kiloTakipList.clear();
        readAllKiloTakip(danisan);
    }

    public ObservableList<Tarif> getListTarif() {
        return listTarif;
    }

    public boolean readAllTarif() {
        return crudFirebase.readAllTarif(listTarif);
    }

    public void updateListTarif() {
        listTarif.clear();
        crudFirebase.readAllTarif(listTarif);
    }

    public Tarif getTarif() {
        return tarif;
    }

    public void setTarif(Tarif tarif) {
        this.tarif = tarif;
    }

    public void updateTarif(Tarif tarif) {
        crudFirebase.updateRecipe(tarif);
    }

    public Danisan getDanisan() {
        return danisan;
    }

    public void setDanisan(Danisan danisan) {
        this.danisan = danisan;
    }

    public String getNameUID(String uid) {
        return crudFirebase.getFullNameByDocumentId(uid);
    }

    public ObservableList<DateRandevu> getListDateRandevu() {
        return listRandevu;
    }

    public boolean readAllRandevu() {return crudFirebase.readAllDate(listRandevu);}

    public void updateListRandevu() {
        listRandevu.clear();
        crudFirebase.readAllDate(listRandevu);
    }

    public void updateListRandevuByDay(String day, String month) {
        updateListRandevu();
        ObservableList<DateRandevu> filteredList = FXCollections.observableArrayList();
        for (DateRandevu dateRandevu : listRandevu) {
            if (dateRandevu.getFirstDay().equals(day) && dateRandevu.getFirstMonth().equals(month)) {
                filteredList.add(dateRandevu);
            }
        }

        listRandevu.setAll(filteredList);
    }

    public void updateListRandevuFromToday() {
        listRandevu.clear();
        readAllRandevu();
        ObservableList<DateRandevu> filteredList = FXCollections.observableArrayList();
        for (DateRandevu dateRandevu : listRandevu) {
            // Şu anki tarihi al
            Date currentDate = new Date();

            // Calendar nesnesini oluştur
            Calendar calendar = Calendar.getInstance();

            // Calendar nesnesini şu anki tarihle ayarla
            calendar.setTime(currentDate);

            if ((calendar.get(Calendar.DAY_OF_MONTH) <= Integer.parseInt(dateRandevu.getFirstDay()) && (calendar.get(Calendar.MONTH) + 1) == Integer.parseInt(dateRandevu.getFirstMonth())) || ((calendar.get(Calendar.MONTH) + 1) <= Integer.parseInt(dateRandevu.getFirstMonth())) || (((calendar.get(Calendar.MONTH) + 1) == 12) && (Integer.parseInt(dateRandevu.getFirstMonth()) >= 1))) {
                filteredList.add(dateRandevu);
            }
        }

        listRandevu.setAll(filteredList);
    }

    public Boolean updateRandevu(DateRandevu date){
        return crudFirebase.updateDate(date);
    }

    public Boolean deleteRandevu(String uid,String firstDay, String firstMonth){
        return crudFirebase.deleteDate(uid,firstDay,firstMonth);
    }

    public String getDocumentIdbyFullName(String fullName){
        return crudFirebase.getDocumentIdByFullName(fullName);
    }

    public Boolean addDateRandevu(DateRandevu dateRandevu){
        return crudFirebase.addDateRandevu(dateRandevu);
    }

    public Boolean addDanisan(Danisan danisan){
        return crudFirebase.addDanisan(danisan);
    }

    public Boolean deleteDanisan(String fullName){
        String documentId = crudFirebase.getDocumentIdByFullName(fullName);
        if(crudFirebase.deleteDanisanByFullName(fullName)){
            crudFirebase.deleteChatsByID(documentId);
            crudFirebase.deleteEatenByID(documentId);
            crudFirebase.deleteDateByID(documentId);
            crudFirebase.deleteWeightTrackingByDocumentId(documentId);
            return true;
        }
        return false;
    }

    public Danisan readDanisanByName(String fullName){
        return crudFirebase.getDanisanByName(fullName);
    }

    public Boolean addWeightTracking(String uid, KiloTakip kiloTakip){
       return crudFirebase.addWeightTracking(uid,kiloTakip);
    }

    public Boolean updateDanisanWeight(Danisan danisan){
        return crudFirebase.updateDanisanWeight(getDocumentIdbyFullName(danisan.getAdiSoyadi()),danisan.getKilo());
    }

    public ObservableList<String> getMapname(){return mapname;}
    public boolean printMapNamesInDocument(String ogun){
        String documentId;
        if(ogun.equals("Kahvaltı")) documentId = "NMKpYuQ6cB5CVCt10ULI";
        else if(ogun.equals("Öğle")) documentId = "lynl1z4Q0jnwHWXXEZHo";
        else if(ogun.equals("Akşam")) documentId = "e6HyWJYIXzpHRrLlW66v";
        else documentId = "fgQumAkXrEKJuIezo2SS";
        return crudFirebase.printMapNamesInDocument(documentId,mapname);
    }
    public void updateMapNames(String ogun) {
        mapname.clear();
        boolean success = printMapNamesInDocument(ogun);
        if (!success) {
            // Hata işleme veya geri bildirim yapma
        }
    }
    //fieldname


    public ObservableList<String> getfieldname(){return fieldname;}
    public boolean printFieldsInMap(String name){
        return crudFirebase.printFieldsInMap(fieldname,name);
    }
    public void updateFieldName(String name) {
        fieldname.clear();
        boolean success = printFieldsInMap(name);
        if (!success) {
            // Hata işleme veya geri bildirim yapma
        }
    }

    public String printFieldValueInMap(String fieldname, String mapname){
        return crudFirebase.printFieldValueInMap(fieldname,mapname);
    }

    public boolean veriEkleDocument(String documentName, String uid, String[][] dizi,String Day,String ogun){
        return crudFirebase.veriEkleDocument(documentName,uid,dizi,Day,ogun);
    }

    public ObservableList<String> getPazartesiDiyetList() {
        return pazartesiDiyetList;
    }

    public ObservableList<String> getSaliDiyetList() {
        return saliDiyetList;
    }

    public ObservableList<String> getCarsambaDiyetList() {
        return carsambaDiyetList;
    }

    public ObservableList<String> getPersembeDiyetList() {
        return persembeDiyetList;
    }

    public ObservableList<String> getCumaDiyetList() {
        return cumaDiyetList;
    }

    public ObservableList<String> getCumartesiDiyetList() {
        return cumartesiDiyetList;
    }

    public ObservableList<String> getPazarDiyetList() {
        return pazarDiyetList;
    }

    public boolean readDiyetList(String day, String uid) {
        switch (day) {
            case "Pazartesi":
                return crudFirebase.veriCekDiyetList1(pazartesiDiyetList, day, uid);
            case "Sali":
                return crudFirebase.veriCekDiyetList1(saliDiyetList, day, uid);
            case "Carsamba":
                return crudFirebase.veriCekDiyetList1(carsambaDiyetList, day, uid);
            case "Persembe":
                return crudFirebase.veriCekDiyetList1(persembeDiyetList, day, uid);
            case "Cuma":
                return crudFirebase.veriCekDiyetList1(cumaDiyetList, day, uid);
            case "Cumartesi":
                return crudFirebase.veriCekDiyetList1(cumartesiDiyetList, day, uid);
            case "Pazar":
                return crudFirebase.veriCekDiyetList1(pazarDiyetList, day, uid);
            default:
                return false;
        }
    }

    public boolean updateListDanisan(String day, String uid) {
        switch (day) {
            case "Pazartesi":
                pazartesiDiyetList.clear();
                return readDiyetList(day, uid);
            case "Sali":
                saliDiyetList.clear();
                return readDiyetList(day, uid);
            case "Carsamba":
                carsambaDiyetList.clear();
                return readDiyetList(day, uid);
            case "Persembe":
                persembeDiyetList.clear();
                return readDiyetList(day, uid);
            case "Cuma":
                cumaDiyetList.clear();
                return readDiyetList(day, uid);
            case "Cumartesi":
                cumartesiDiyetList.clear();
                return readDiyetList(day, uid);
            case "Pazar":
                pazarDiyetList.clear();
                return readDiyetList(day, uid);
            default:
                return false;
        }
    }

    public ObservableList<String> getChatNames() {
        return chatNameList;
    }

    public boolean readAllChatNames() {
        return crudFirebase.getAllChatNames(chatNameList);
    }

    public void updateChatNames() {
        chatNameList.clear();
        readAllChatNames();
    }

    public ObservableList<Chat> getChatList() {
        ObservableList<Chat> chatList = FXCollections.observableArrayList(this.chatList);

        chatList.sort(Comparator.comparing(Chat::getTime));

        return chatList;
    }

    public boolean readAllChatList(String chatName) {
        return crudFirebase.readChatsByChatName(chatList,chatName);
    }

    public void updateChatList(String chatName) {
        chatList.clear();
        readAllChatList(chatName);
    }

    public void sendChatMessage(String chatName, Chat chat){
        crudFirebase.sendChatMessage(chatName,chat);
    }

    //diyetGonderenler


    public ObservableList<String> getListDiyetGonderenler() {
        return diyetGonderenler;
    }

    public boolean readDiyetGonderenler() {return crudFirebase.getEatenNames(getTodayDay(),diyetGonderenler);}

    public void updateDiyetGonderenler() {
        diyetGonderenler.clear();
        if (readDiyetGonderenler()) {
            Set<String> uniqueNames = new HashSet<>(diyetGonderenler);
            diyetGonderenler.setAll(uniqueNames);
        }
    }
     private String getTodayDay() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
        String day = now.format(formatter);

        if (day.length() == 1) {
            day = "0" + day;
        }
        System.out.println("Gün:" + day);
        return day;
    }
    public void setIsim(String isim){this.isim = isim; }

    public String getIsim(){return isim;}


}
