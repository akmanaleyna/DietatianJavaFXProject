package com.example.dietatianjavafx.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class KiloTakip {

    private final StringProperty tarih;
    private final StringProperty kilo ;

    public KiloTakip(String tarih, String kilo) {
        this.tarih = new SimpleStringProperty(tarih);
        this.kilo = new SimpleStringProperty(kilo);
    }

    public String getTarih() {
        return tarih.get();
    }

    public StringProperty tarihProperty() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih.set(tarih);
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
}
