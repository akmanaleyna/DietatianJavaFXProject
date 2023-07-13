package com.example.dietatianjavafx.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Eaten {
    private final StringProperty eaten;
    private final StringProperty oguntime;
    private final StringProperty calories;


    public Eaten(String eaten, String oguntime, String calories) {
        this.eaten = new SimpleStringProperty(eaten);
        this.oguntime = new SimpleStringProperty(oguntime);
        this.calories = new SimpleStringProperty(calories);
    }

    public Eaten() {
        this.eaten = new SimpleStringProperty("");
        this.oguntime = new SimpleStringProperty("");
        this.calories = new SimpleStringProperty("");
    }

    public String getEaten() {
        return eaten.get();
    }

    public StringProperty eatenProperty() {
        return eaten;
    }

    public void setEaten(String eaten) {
        this.eaten.set(eaten);
    }

    public String getOguntime() {
        return oguntime.get();
    }

    public StringProperty oguntimeProperty() {
        return oguntime;
    }

    public void setOguntime(String oguntime) {
        this.oguntime.set(oguntime);
    }

    public String getCalories() {
        return calories.get();
    }

    public StringProperty caloriesProperty() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories.set(calories);
    }
}
