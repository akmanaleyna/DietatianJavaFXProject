package com.example.dietatianjavafx.Models;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aleyn
 */
public interface DatabaseConnectionAdapter<I,C> {
    public String createConnection(C c);
    public I getConnection();
}
