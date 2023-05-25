package com.example.dietatianjavafx.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Danisan {
    private final StringProperty adiSoyadi;
    private final IntegerProperty guncelKilo;

    public Danisan(String adiSoyadi, int guncelKilo){
        this.adiSoyadi = new SimpleStringProperty(this,"adiSoyadi",adiSoyadi);
        this.guncelKilo = new SimpleIntegerProperty(this,"guncelKilo",guncelKilo);
    }

    public StringProperty adiSoyadiProperty(){return this.adiSoyadi;}
    public IntegerProperty guncelKiloProperty(){return this.guncelKilo;}

}
