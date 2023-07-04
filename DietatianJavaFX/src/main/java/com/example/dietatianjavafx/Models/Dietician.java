package com.example.dietatianjavafx.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Dietician {
    private StringProperty adiSoyadi, sifre, email ;

    public Dietician(String adiSoyadi, String sifre, String email) {
        this.adiSoyadi = new SimpleStringProperty(adiSoyadi);
        this.sifre = new SimpleStringProperty(sifre);
        this.email = new SimpleStringProperty(email);
    }
    /*
    public Dietician(String email){
        this.email = new SimpleStringProperty(email);
    }

     */
    public String getAdiSoyadi() {
        return adiSoyadi.get();
    }

    public StringProperty adiSoyadiProperty() {
        return adiSoyadi;
    }

    public void setAdiSoyadi(String adiSoyadi) {
        this.adiSoyadi.set(adiSoyadi);
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

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}
