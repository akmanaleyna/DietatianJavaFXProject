/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.example.dietatianjavafx.Models;

/**
 *
 * @author aleyn
 */
public enum DatabaseCredentialsFirebase {
    SEVICECONNECTION(),
    URLDATABASE("https://diyet-dcb4e.firebaseio.com/");
    
    private final String VALUE;

    private DatabaseCredentialsFirebase(String VALUE) {
        this.VALUE = VALUE;
    }

    public String getVALUE() {
        return VALUE;
    }  
}
