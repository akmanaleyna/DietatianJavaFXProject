package com.example.dietatianjavafx.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DateRandevu {

    private final StringProperty firstDay;
    private final StringProperty firstMonth;
    private final StringProperty isConfirment;
    private final StringProperty uid;
    private final StringProperty confirmedDate;
    private final StringProperty dietationID;
    private final StringProperty doctorName;
    private final StringProperty lastDay;
    private final StringProperty lastMonth;

    public DateRandevu(String firstDay, String firstMonth, String isConfirment, String uid, String confirmedDate, String dietationID, String doctorName, String lastDay, String lastMonth) {
        this.firstDay = new SimpleStringProperty(firstDay);
        this.firstMonth = new SimpleStringProperty(firstMonth);
        this.isConfirment = new SimpleStringProperty(isConfirment);
        this.uid = new SimpleStringProperty(uid);
        this.confirmedDate = new SimpleStringProperty(confirmedDate);
        this.dietationID = new SimpleStringProperty(dietationID);
        this.doctorName = new SimpleStringProperty(doctorName);
        this.lastDay = new SimpleStringProperty(lastDay);
        this.lastMonth = new SimpleStringProperty(lastMonth);
    }

    public String getConfirmedDate() {
        return confirmedDate.get();
    }

    public StringProperty confirmedDateProperty() {
        return confirmedDate;
    }

    public void setConfirmedDate(String confirmedDate) {
        this.confirmedDate.set(confirmedDate);
    }

    public String getDietationID() {
        return dietationID.get();
    }

    public StringProperty dietationIDProperty() {
        return dietationID;
    }

    public void setDietationID(String dietationID) {
        this.dietationID.set(dietationID);
    }

    public String getDoctorName() {
        return doctorName.get();
    }

    public StringProperty doctorNameProperty() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName.set(doctorName);
    }

    public String getLastDay() {
        return lastDay.get();
    }

    public StringProperty lastDayProperty() {
        return lastDay;
    }

    public void setLastDay(String lastDay) {
        this.lastDay.set(lastDay);
    }

    public String getLastMonth() {
        return lastMonth.get();
    }

    public StringProperty lastMonthProperty() {
        return lastMonth;
    }

    public void setLastMonth(String lastMonth) {
        this.lastMonth.set(lastMonth);
    }

    public String getFirstDay() {
        return firstDay.get();
    }

    public StringProperty firstDayProperty() {
        return firstDay;
    }

    public void setFirstDay(String firstDay) {
        this.firstDay.set(firstDay);
    }

    public String getFirstMonth() {
        return firstMonth.get();
    }

    public StringProperty firstMonthProperty() {
        return firstMonth;
    }

    public void setFirstMonth(String firstMonth) {
        this.firstMonth.set(firstMonth);
    }

    public String getIsConfirment() {
        return isConfirment.get();
    }

    public StringProperty isConfirmentProperty() {
        return isConfirment;
    }

    public void setIsConfirment(String isConfirment) {
        this.isConfirment.set(isConfirment);
    }

    public String getUid() {
        return uid.get();
    }

    public StringProperty uidProperty() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid.set(uid);
    }
}
