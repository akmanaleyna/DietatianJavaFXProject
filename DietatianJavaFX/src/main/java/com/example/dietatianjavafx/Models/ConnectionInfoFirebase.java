/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dietatianjavafx.Models;

/**
 *
 * @author aleyn
 */

public class ConnectionInfoFirebase {
    private String credetials;
    private String path;
    
    public ConnectionInfoFirebase(String credetials, String path){
        this.credetials = credetials;
        this.path = path;
    }
    public String getCredetials() {
        return credetials;
    }

    public void setCredetials(String credetials) {
        this.credetials = credetials;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
