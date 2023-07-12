package com.example.dietatianjavafx.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Tarif {
    private StringProperty tarifAdi, tarifIcerik, tarifAdim, tarifKalorisi, tarifResmi;

    public Tarif(String tarifAdi, String tarifIcerik, String tarifAdim, String tarifKalorisi, String tarifResmi) {
        this.tarifAdi = new SimpleStringProperty(tarifAdi);
        this.tarifIcerik = new SimpleStringProperty(tarifIcerik);
        this.tarifAdim = new SimpleStringProperty(tarifAdim);
        this.tarifKalorisi = new SimpleStringProperty(tarifKalorisi);
        this.tarifResmi = new SimpleStringProperty(tarifResmi);
    }

    public String getTarifAdi() {
        return tarifAdi.get();
    }

    public StringProperty tarifAdiProperty() {
        return tarifAdi;
    }

    public void setTarifAdi(String tarifAdi) {
        this.tarifAdi.set(tarifAdi);
    }

    public String getTarifIcerik() {
        return tarifIcerik.get();
    }

    public StringProperty tarifIcerikProperty() {
        return tarifIcerik;
    }

    public void setTarifIçerik(String tarifIcerik) {
        this.tarifIcerik.set(tarifIcerik);
    }

    public String getTarifAdim() {
        return tarifAdim.get();
    }

    public StringProperty tarifAdimProperty() {
        return tarifAdim;
    }

    public void setTarifAdim(String tarifAdim) {
        this.tarifAdim.set(tarifAdim);
    }

    public String getTarifKalorisi() {
        return tarifKalorisi.get();
    }

    public StringProperty tarifKalorisiProperty() {
        return tarifKalorisi;
    }

    public void setTarifKalorisi(String tarifKalorisi) {
        this.tarifKalorisi.set(tarifKalorisi);
    }

    public String getTarifResmi() {
        return tarifResmi.get();
    }

    public StringProperty tarifResmiProperty() {
        return tarifResmi;
    }

    public void setTarifResmi(String tarifResmi) {
        this.tarifResmi.set(tarifResmi);
    }
}
