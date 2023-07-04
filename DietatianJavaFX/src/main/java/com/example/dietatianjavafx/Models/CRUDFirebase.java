package com.example.dietatianjavafx.Models;

import com.google.cloud.firestore.*;
import com.google.api.core.ApiFuture;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.util.*;
import java.util.concurrent.ExecutionException;


public class CRUDFirebase {


    private Firestore db;

    public CRUDFirebase() {
        db = DatabaseConnectionFirebase.getInstance().getConnection();
    }
    private boolean key = false;

    public Boolean readDanisan(){
        key = false;
        // asynchronously retrieve all documents
        // test collection'u alıyor
        ApiFuture<QuerySnapshot> future = db.collection("users").get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = null;
        try {

            documents = future.get().getDocuments();
            if(documents.size() > 0)
            {
                System.out.println("Veriler..");
                for (QueryDocumentSnapshot document : documents) {
                    //                                            test collectiondaki Numara verisini alıyor
                    System.out.println(document.getId() + " => " + document.getData().get("email") + " " + document.getData().get("fullName")+ " " + document.getData().get("gender")+ " " + document.getData().get("height")+ " " + document.getData().get("weight"));
                }
            }else
                System.out.println("Veritabanı boş");
            key = true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return key;
    }
    public Boolean addDanisan(Danisan danisan){
        key = false;
        Map<String, Object> docdanisan;
        docdanisan = new HashMap<>();
        docdanisan.put("fullName", danisan.getAdiSoyadi());
        docdanisan.put("email", danisan.getEmail());
        docdanisan.put("gender", danisan.getCinsiyet());
        docdanisan.put("age", danisan.getYas());
        docdanisan.put("height", danisan.getBoy());
        docdanisan.put("weight", danisan.getKilo());
        docdanisan.put("targetWeight", danisan.getKilo());
        docdanisan.put("diseases", danisan.getHastaliklar());
        docdanisan.put("note", danisan.getNot());
        docdanisan.put("dietationId", "");
        docdanisan.put("profilePic", "");
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(danisan.getEmail())
                .setPassword(danisan.getSifre()); // replace with actual password

        try {

            FirebaseAuth auth = FirebaseAuth.getInstance();


            UserRecord userRecord = auth.createUser(request);
            System.out.println("User created: " + userRecord.getUid());
            docdanisan.put("uid", userRecord.getUid());
            ApiFuture<WriteResult> future = db.collection("users").document(userRecord.getUid()).set(docdanisan);

            System.out.println("Update time : " + future.get().getUpdateTime());
            key = true;
        } catch (Exception e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
        return key;
    }
    public Boolean addDietician(Dietician dietician){
        key = false;
        Map<String, Object> docDietisyen;
        docDietisyen = new HashMap<>();
        docDietisyen.put("adSoyad", dietician.getAdiSoyadi());
        docDietisyen.put("email", dietician.getEmail());
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(dietician.getEmail())
                .setPassword(dietician.getSifre()); // replace with actual password

        try {

            FirebaseAuth auth = FirebaseAuth.getInstance();


            UserRecord userRecord = auth.createUser(request);
            System.out.println("User created: " + userRecord.getUid());
            docDietisyen.put("uid", userRecord.getUid());
            ApiFuture<WriteResult> future = db.collection("dietician").document(userRecord.getUid()).set(docDietisyen);

            System.out.println("Update time : " + future.get().getUpdateTime());
            key = true;
        } catch (Exception e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
        return key;
    }
    public int getMotivasyonFieldCount() {
        int fieldCount = 0;

        // Asenkron olarak tüm belgeleri al
        ApiFuture<QuerySnapshot> future = db.collection("motivation").get();
        try {
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            if (!documents.isEmpty()) {
                // İlk belgeyi al
                QueryDocumentSnapshot document = documents.get(0);

                // Belge içerisindeki alan sayısını hesapla
                fieldCount = document.getData().size();
            } else {
                System.out.println("Motivasyon cümlesi yok");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return fieldCount;
    }
    public Boolean addMotivasyon(String motivation){
        boolean key = false;

        try {
            DocumentReference docRef = db.collection("motivation").document("dgr4i7twwGhXADUaAPI1");
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                Map<String, Object> data = document.getData();
                data.put(String.valueOf(getMotivasyonFieldCount() + 1), motivation);

                ApiFuture<WriteResult> updateFuture = docRef.set(data);
                System.out.println("Güncelleme zamanı: " + updateFuture.get().getUpdateTime());
                key = true;
            } else {
                System.out.println("Belge bulunamadı");
            }
        } catch (Exception e) {
            System.out.println("Hata oluştu: " + e.getMessage());
        }

        return key;
    }
    public String readMotivasyon(){

        // Asenkron olarak tüm belgeleri al
        ApiFuture<QuerySnapshot> future = db.collection("motivation").get();
        try {
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            if (!documents.isEmpty()) {
                System.out.println("Veriler..");

                for (QueryDocumentSnapshot document : documents) {
                    Random random = new Random();
                    if (document.contains(String.valueOf(random.nextInt(getMotivasyonFieldCount()) + 1))) {
                        String text = document.getString(String.valueOf(random.nextInt(getMotivasyonFieldCount()) + 1));
                        //System.out.println(document.getId() + " => " + document.getData().get(String.valueOf(random.nextInt(getMotivasyonFieldCount()) + 1)));
                        return String.valueOf(document.getData().get(String.valueOf(random.nextInt(getMotivasyonFieldCount()) + 1)));
                    }
                }
            } else {
                System.out.println("Veritabanı boş");
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }
    /*
    public Boolean loginDietician(Dietician dietician)  {
        key = false;

        UserRecord userRecord = null;
        try {
            userRecord = FirebaseAuth.getInstance().getUserByEmail(dietician.getEmail().toString());
            // See the UserRecord reference doc for the contents of userRecord.
            System.out.println("Successfully fetched user data: " + userRecord.getUid() + " " + userRecord.getEmail());
            key = true;
        } catch (FirebaseAuthException e) {
            throw new RuntimeException(e);
        }


        return key;
    }
     */

}
