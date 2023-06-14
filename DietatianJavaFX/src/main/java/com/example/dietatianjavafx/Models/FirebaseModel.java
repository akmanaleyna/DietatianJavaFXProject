package com.example.dietatianjavafx.Models;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseModel {
    private DatabaseReference database;
    private FirebaseAuth firebaseAuth;

    public FirebaseModel(){
        //initializeFirebase();
    }

    private void initializeFirebase() {
        try {
            FileInputStream serviceAccount = new FileInputStream("Service.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://diyet-dcb4e-default-rtdb.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
            database = FirebaseDatabase.getInstance().getReference();
            firebaseAuth = FirebaseAuth.getInstance(); // Firebase Authentication nesnesini al
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //public boolean login(String email, String password) throws FirebaseAuthException {
        /*
        try {
            // Firebase Authentication ile giriş yap
            firebaseAuth.signInWithEmailAndPassword(email, password);

            // Giriş işlemi başarılı ise, kullanıcının token'ını doğrula
            // String token = firebaseAuth.getAccessToken(); // Eski hatalı satır, kaldırıldı

            // Oturum açmış kullanıcının bilgilerini al
            FirebaseToken firebaseToken = firebaseAuth.getUid();
            String userId = firebaseToken.getUid();
            String userEmail = firebaseToken.getEmail();

            // Giriş işlemi başarılı
            return true;
        } catch (FirebaseAuthException e) {
            // Giriş işlemi başarısız oldu, hata mesajını ele alabilirsiniz
            e.printStackTrace();
            return false;
        }

         */
    }

