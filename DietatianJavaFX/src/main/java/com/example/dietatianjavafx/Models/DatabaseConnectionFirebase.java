/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dietatianjavafx.Models;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
/**
 *
 * @author aleyn
 */
public final class DatabaseConnectionFirebase {

    private static Firestore db;
    private static DatabaseConnectionFirebase instance;

    private DatabaseConnectionFirebase() {
        // FirebaseApp başlatma işlemleri
        try {
            InputStream serviceAccount = new ByteArrayInputStream(DatabaseCredentialsFirebase.SEVICECONNECTION.getVALUE().getBytes(StandardCharsets.UTF_8));
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            FirebaseApp.initializeApp(options);
            db = FirestoreClient.getFirestore();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized DatabaseConnectionFirebase getInstance() {
        if (instance == null) {
            instance = new DatabaseConnectionFirebase();
        }
        return instance;
    }

    public Firestore getConnection() {
        return db;
    }
    
}
