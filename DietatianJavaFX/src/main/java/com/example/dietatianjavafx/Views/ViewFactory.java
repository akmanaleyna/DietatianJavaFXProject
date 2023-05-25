package com.example.dietatianjavafx.Views;

import com.example.dietatianjavafx.Controllers.OpenAllPageController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {
    private final StringProperty pageSelectedMenuItem;
    private AnchorPane dashboardView;
    private AnchorPane danisanlar;
    private AnchorPane saglikliTarifler;
    private AnchorPane randevularim;
    private AnchorPane chat;
    private AnchorPane videoCall;
    private AnchorPane ayarlar;

    public ViewFactory(){
        this.pageSelectedMenuItem = new SimpleStringProperty("");
    }

    public StringProperty getPageSelectedMenuItem() {
        return pageSelectedMenuItem;
    }

    public AnchorPane getDashboardView() {
        if(dashboardView == null){
            try{
                dashboardView = new FXMLLoader(getClass().getResource("/Fxml/HomePage.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return dashboardView;
    }

    public AnchorPane getDanisanlar() {
        if(danisanlar == null){
            try{
                danisanlar = new FXMLLoader(getClass().getResource("/Fxml/Danisanlar.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return danisanlar;
    }

    public AnchorPane getSaglikliTarifler() {
        if(saglikliTarifler == null){
            try{
                saglikliTarifler = new FXMLLoader(getClass().getResource("/Fxml/SaglikliTarifler.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return saglikliTarifler;
    }

    public AnchorPane getRandevularim() {
        if(randevularim == null){
            try{
                randevularim = new FXMLLoader(getClass().getResource("/Fxml/Randevularım.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return randevularim;
    }

    public AnchorPane getChat() {
        if(chat == null){
            try{
                chat = new FXMLLoader(getClass().getResource("/Fxml/Chat.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return chat;
    }

    public AnchorPane getVideoCall() {
        if(videoCall == null){
            try{
                videoCall = new FXMLLoader(getClass().getResource("/Fxml/VideoCall.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return videoCall;
    }

    public AnchorPane getAyarlar() {
        if(ayarlar == null){
            try{
                ayarlar = new FXMLLoader(getClass().getResource("/Fxml/Ayarlar.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return ayarlar;
    }

    @SuppressWarnings("ReassignedVariable")
    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }
    public void showHomePage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/OpenAllPage.fxml"));
        OpenAllPageController openAllPageController = new OpenAllPageController();
        loader.setController(openAllPageController);
        createStage(loader);
    }
    public void showYeniDanisanEkle(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/YeniDanisanEkle.fxml"));
        createStage(loader);
    }
    public void showDanisanIncele(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Danisanİncele.fxml"));
        createStage(loader);
    }
    public void showDiyetYazma(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/DiyetYazma.fxml"));
        createStage(loader);
    }
    private void createStage(FXMLLoader loader){
        Scene scene = null;
        try{
            scene = new Scene(loader.load());
        }catch (Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Dietitian");
        stage.setResizable(false);
        stage.show();
    }
    public void closeStage(Stage stage){
        stage.close();

    }
}
