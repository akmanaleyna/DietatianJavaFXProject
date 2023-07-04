package com.example.dietatianjavafx.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Danisan {
    private final StringProperty adiSoyadi;
    private final StringProperty yas;
    private final StringProperty email;
    private final StringProperty cinsiyet;
    private final StringProperty boy;
    private final StringProperty not;
    private final StringProperty hedefKilo;
    private final StringProperty hastaliklar;
    private final StringProperty kilo;
    private final StringProperty sifre;

    public Danisan(String adiSoyadi, String hedefKilo, String yas, String email, String sifre, String cinsiyet, String boy, String kilo, String not, String hastaliklar){
        this.adiSoyadi = new SimpleStringProperty(adiSoyadi);
        this.yas = new SimpleStringProperty(yas);
        this.email = new SimpleStringProperty(email);
        this.cinsiyet = new SimpleStringProperty(cinsiyet);
        this.boy = new SimpleStringProperty(boy);
        this.not = new SimpleStringProperty(not);
        this.hedefKilo = new SimpleStringProperty(hedefKilo);
        this.hastaliklar = new SimpleStringProperty(hastaliklar);
        this.kilo = new SimpleStringProperty(kilo);
        this.sifre = new SimpleStringProperty(sifre);
        StringProperty sifre1 = new SimpleStringProperty(sifre);
    }

    public String getSifre() {
        return sifre.get();
    }

    public StringProperty sifreProperty() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre.set(sifre);
    }

    public String getHedefKilo() {
        return hedefKilo.get();
    }

    public StringProperty hedefKiloProperty() {
        return hedefKilo;
    }

    public void setHedefKilo(String hedefKilo) {
        this.hedefKilo.set(hedefKilo);
    }

    public String getKilo() {
        return kilo.get();
    }

    public StringProperty kiloProperty() {
        return kilo;
    }

    public void setKilo(String kilo) {
        this.kilo.set(kilo);
    }

    public String getAdiSoyadi() {
        return adiSoyadi.get();
    }

    public StringProperty adiSoyadiProperty() {
        return adiSoyadi;
    }

    public void setAdiSoyadi(String adiSoyadi) {
        this.adiSoyadi.set(adiSoyadi);
    }

    public String getYas() {
        return yas.get();
    }

    public StringProperty yasProperty() {
        return yas;
    }

    public void setYas(String yas) {
        this.yas.set(yas);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getCinsiyet() {
        return cinsiyet.get();
    }

    public StringProperty cinsiyetProperty() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet.set(cinsiyet);
    }

    public String getBoy() {
        return boy.get();
    }

    public StringProperty boyProperty() {
        return boy;
    }

    public void setBoy(String boy) {
        this.boy.set(boy);
    }

    public String getNot() {
        return not.get();
    }

    public StringProperty notProperty() {
        return not;
    }

    public void setNot(String not) {
        this.not.set(not);
    }

    public String getHastaliklar() {
        return hastaliklar.get();
    }

    public StringProperty hastaliklarProperty() {
        return hastaliklar;
    }

    public void setHastaliklar(String hastaliklar) {
        this.hastaliklar.set(hastaliklar);
    }
}
