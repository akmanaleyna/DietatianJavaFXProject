package com.example.dietatianjavafx.Models;

import com.google.cloud.firestore.*;
import com.google.api.core.ApiFuture;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.GenericTypeIndicator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class CRUDFirebase {

    private Firestore db;

    public CRUDFirebase() {
        db = DatabaseConnectionFirebase.getInstance().getConnection();
    }

    private boolean key = false;

    private Danisan danisan;

    private Tarif tarif;


    public boolean readAllDanisan(ObservableList<Danisan> listDanisan) {
        boolean key = false;
        // CollectionReference oluştur
        CollectionReference collectionReference = db.collection("users");

        // Verileri getir
        try {
            ApiFuture<QuerySnapshot> future = collectionReference.get();
            QuerySnapshot querySnapshot = future.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            if (!documents.isEmpty()) {
                System.out.println("Veriler..");
                for (QueryDocumentSnapshot document : documents) {
                    String fullName = document.getString("fullName");
                    String targetWeight = document.getString("targetWeight");
                    String age = document.getString("age");
                    String email = document.getString("email");
                    String gender = document.getString("gender");
                    String height = document.getString("height");
                    String weight = document.getString("weight");
                    String note = document.getString("note");
                    String diseases = document.getString("diseases");

                    Danisan danisan = new Danisan(fullName, targetWeight, age, email, "", gender, height, weight, note, diseases);
                    listDanisan.add(danisan);
                }
            } else {
                System.out.println("Veritabanı boş");
            }
            key = true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return key;
    }

    public Danisan getDanisanByName(String danisanAdi) {
        // CollectionReference oluştur
        CollectionReference collectionReference = db.collection("users");

        // Danışanı bul
        try {
            Query query = collectionReference.whereEqualTo("fullName", danisanAdi);
            ApiFuture<QuerySnapshot> future = query.get();
            QuerySnapshot querySnapshot = future.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

            if (!documents.isEmpty()) {
                QueryDocumentSnapshot document = documents.get(0); // İlk eşleşen dokümanı al

                String fullName = document.getString("fullName");
                String targetWeight = document.getString("targetWeight");
                String age = document.getString("age");
                String email = document.getString("email");
                String gender = document.getString("gender");
                String height = document.getString("height");
                String weight = document.getString("weight");
                String note = document.getString("note");
                String diseases = document.getString("diseases");

                return new Danisan(fullName, targetWeight, age, email, "", gender, height, weight, note, diseases);
            } else {
                System.out.println("Danışan bulunamadı: " + danisanAdi);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return null; // Danışan bulunamazsa null döndür
    }


    public Boolean getWeightTrackingByDocumentId(String uid, ObservableList<KiloTakip> kiloTakipList) {
        key = false;
        try {
            DocumentReference docRef = db.collection("weightTracking").document(uid);
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                Map<String, Object> data = document.getData();
                for (Map.Entry<String, Object> entry : data.entrySet()) {
                    String tarih = entry.getKey();
                    Map<String, Object> kiloTakipData = (Map<String, Object>) entry.getValue();
                    String kilo = (String) kiloTakipData.get("kilo");
                    KiloTakip kiloTakip = new KiloTakip(tarih, kilo);
                    kiloTakipList.add(kiloTakip);
                }
            } else System.out.println("Veritabanı boş");
            key = true;
        } catch (Exception e) {
            System.out.println("Error retrieving weight tracking data: " + e.getMessage());
        }
        return key;
    }


    public boolean readAllTarif(ObservableList<Tarif> listTarif) {
        boolean key = false;
        CollectionReference collectionReference = db.collection("recipe");

        try {
            ApiFuture<QuerySnapshot> future = collectionReference.get();
            QuerySnapshot querySnapshot = future.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            if (!documents.isEmpty()) {
                System.out.println("Veriler..");
                for (QueryDocumentSnapshot document : documents) {
                    String recipeName = document.getString("recipeName");
                    String ingredients = document.getString("ingredients");
                    String recipeSteps = document.getString("recipeSteps");
                    String recipeImages = document.getString("recipeImages");
                    String calories = document.getString("calories");

                    Tarif tarif = new Tarif(recipeName, ingredients, recipeSteps, recipeImages, calories);
                    listTarif.add(tarif);
                }
            } else {
                System.out.println("Veritabanı boş");
            }
            key = true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return key;
    }

    public boolean readAllDate(ObservableList<DateRandevu> listDate) {
        boolean key = false;
        CollectionReference collectionReference = db.collection("date");

        try {
            ApiFuture<QuerySnapshot> future = collectionReference.get();
            QuerySnapshot querySnapshot = future.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            if (!documents.isEmpty()) {
                System.out.println("Veriler..");
                for (QueryDocumentSnapshot document : documents) {
                    String firstDay = document.getString("firstDay");
                    String firstMonth = document.getString("firstMonth");
                    String isConfirmation = document.getString("isConfirmed");
                    String uid = document.getString("uid");
                    String confirmedDate = document.getString("confirmedDate");
                    String dietationID = document.getString("dietationID");
                    String doctorName = document.getString("doctorName");
                    String lastDay = document.getString("lastDay");
                    String lastMonth = document.getString("lastMonth");
                    DateRandevu date = new DateRandevu(firstDay, firstMonth, isConfirmation, uid, confirmedDate, dietationID, doctorName, lastDay, lastMonth);
                    listDate.add(date);
                }
            } else {
                System.out.println("Veritabanı boş");
            }
            key = true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return key;
    }


    public Tarif getTarifByRecipeName(String recipeName) {
        CollectionReference collectionReference = db.collection("recipe");

        try {
            Query query = collectionReference.whereEqualTo("recipeName", recipeName);
            ApiFuture<QuerySnapshot> future = query.get();
            QuerySnapshot querySnapshot = future.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            if (!documents.isEmpty()) {
                for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                    String ingredients = document.getString("ingredients");
                    String recipeSteps = document.getString("recipeSteps");
                    String recipeImages = document.getString("recipeImages");
                    String calories = document.getString("calories");
                    tarif = new Tarif(recipeName, ingredients, recipeSteps, calories, recipeImages);
                }
            } else {
                System.out.println("Tarif bulunamadı.");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return tarif;
    }

    public Boolean addDateRandevu(DateRandevu date) {
        key = false;
        Map<String, Object> docDate = new HashMap<>();
        docDate.put("firstDay", date.getFirstDay());
        docDate.put("firstMonth", date.getFirstMonth());
        docDate.put("isConfirmed", date.getIsConfirment());
        docDate.put("uid", date.getUid());
        docDate.put("confirmedDate", date.getConfirmedDate());
        docDate.put("dietationID", date.getDietationID());
        docDate.put("doctorName", date.getDoctorName());
        docDate.put("lastDay", date.getLastDay());
        docDate.put("lastMonth", date.getLastMonth());

        try {
            ApiFuture<WriteResult> future = db.collection("date").document(UUID.randomUUID().toString()).set(docDate);
            System.out.println("Update time: " + future.get().getUpdateTime());
            key = true;
        } catch (Exception e) {
            System.out.println("Error adding date: " + e.getMessage());
        }

        return key;
    }

    public boolean updateDate(DateRandevu date) {
        // Koleksiyon referansını alın
        CollectionReference dateCollectionRef = db.collection("date");

        // Belirtilen uid'ye sahip olan belgenin alanlarını güncelleyin
        ApiFuture<QuerySnapshot> future = dateCollectionRef.whereEqualTo("uid", date.getUid())
                .whereEqualTo("firstDay", date.getFirstDay())
                .whereEqualTo("firstMonth", date.getFirstMonth())
                .whereEqualTo("isConfirmed", "false")
                .get();
        ;
        try {
            QuerySnapshot querySnapshot = future.get();

            if (!querySnapshot.isEmpty()) {
                DocumentSnapshot snapshot = querySnapshot.getDocuments().get(0);

                Map<String, Object> updates = new HashMap<>();
                updates.put("firstDay", date.getFirstDay());
                updates.put("firstMonth", date.getFirstMonth());
                updates.put("isConfirmed", "true");
                updates.put("uid", date.getUid());
                updates.put("confirmedDate", date.getConfirmedDate());
                updates.put("dietationID", date.getDietationID());
                updates.put("doctorName", date.getDoctorName());
                updates.put("lastDay", date.getLastDay());
                updates.put("lastMonth", date.getLastMonth());

                snapshot.getReference().update(updates);
                key = true;
            } else {
                key = false;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            key = false;
        }

        return key;
    }

    public boolean deleteDate(String uid, String firstDay, String firstMonth) {
        // Koleksiyon referansını alın
        CollectionReference dateCollectionRef = db.collection("date");

        // Belirtilen uid'ye sahip olan belgeyi silin
        ApiFuture<QuerySnapshot> future = dateCollectionRef.whereEqualTo("uid", uid)
                .whereEqualTo("firstDay", firstDay)
                .whereEqualTo("firstMonth", firstMonth)
                .get();
        try {
            QuerySnapshot querySnapshot = future.get();

            if (!querySnapshot.isEmpty()) {
                DocumentSnapshot snapshot = querySnapshot.getDocuments().get(0);
                snapshot.getReference().delete();
                key = true;
            } else {
                key = false;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            key = false;
        }

        return key;
    }

    public Boolean addWeightTracking(String uid, KiloTakip kiloTakip) {
        boolean key = false;

        Map<String, Object> kiloTakipData = new HashMap<>();
        kiloTakipData.put("tarih", kiloTakip.getTarih());
        kiloTakipData.put("kilo", kiloTakip.getKilo());

        try {
            DocumentReference docRef = db.collection("weightTracking").document(uid);
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                // Belge varsa, tüm verileri güncelle
                Map<String, Object> existingData = document.getData();
                existingData.put(kiloTakip.getTarih(), kiloTakipData);
                ApiFuture<WriteResult> updateFuture = docRef.set(existingData);
                System.out.println("Update time: " + updateFuture.get().getUpdateTime());
            } else {
                // Belge yoksa, yeni bir belge oluştur ve verileri ekle
                Map<String, Object> newData = new HashMap<>();
                newData.put(kiloTakip.getTarih(), kiloTakipData);
                ApiFuture<WriteResult> setFuture = docRef.set(newData);
                System.out.println("Create time: " + setFuture.get().getUpdateTime());
            }
            key = true;
        } catch (Exception e) {
            System.out.println("Error adding date: " + e.getMessage());
        }

        return key;
    }

    public Boolean updateRecipe(Tarif tarif) {
        // Koleksiyon referansını alın
        CollectionReference recipeCollectionRef = db.collection("recipe");

        // recipeName'i belirtilen değere (örneğin "elma") sahip olan belgenin alanlarını güncelleyin
        ApiFuture<QuerySnapshot> future = recipeCollectionRef.whereEqualTo("recipeName", tarif.getTarifAdi().toString()).get();
        try {
            QuerySnapshot querySnapshot = future.get();

            if (!querySnapshot.isEmpty()) {
                DocumentSnapshot snapshot = querySnapshot.getDocuments().get(0);

                Map<String, Object> updates = new HashMap<>();
                updates.put("ingredients", tarif.getTarifIcerik().toString());
                updates.put("recipeSteps", tarif.getTarifAdim().toString());
                updates.put("recipeImages", "");
                updates.put("calories", tarif.getTarifKalorisi().toString());

                snapshot.getReference().set(updates, SetOptions.merge());
                key = true;
            } else {
                key = false;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            key = false;
        }

        return key;
    }

    public Boolean addDanisan(Danisan danisan) {
        key = false;
        Map<String, Object> docdanisan;
        docdanisan = new HashMap<>();
        docdanisan.put("fullName", danisan.getAdiSoyadi());
        docdanisan.put("email", danisan.getEmail());
        docdanisan.put("gender", danisan.getCinsiyet());
        docdanisan.put("age", danisan.getYas());
        docdanisan.put("height", danisan.getBoy());
        docdanisan.put("weight", danisan.getKilo());
        docdanisan.put("targetWeight", danisan.getHedefKilo());
        docdanisan.put("diseases", danisan.getHastaliklar());
        docdanisan.put("note", danisan.getNot());
        docdanisan.put("dietationId", "");
        docdanisan.put("profilePic", "");

        String chatUid = UUID.randomUUID().toString();
        String eatenUid = UUID.randomUUID().toString();

        List<String> chats = new ArrayList<>();
        chats.add(chatUid);
        docdanisan.put("chats", chats);

        List<String> eaten = new ArrayList<>();
        eaten.add(eatenUid);
        docdanisan.put("eaten", eaten);


        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(danisan.getEmail())
                .setPassword(danisan.getSifre()); // replace with actual password

        try {

            FirebaseAuth auth = FirebaseAuth.getInstance();


            UserRecord userRecord = auth.createUser(request);
            System.out.println("User created: " + userRecord.getUid());
            docdanisan.put("uid", userRecord.getUid());
            ApiFuture<WriteResult> future = db.collection("users").document(userRecord.getUid()).set(docdanisan);

            addEaten(eatenUid, danisan.getAdiSoyadi(), userRecord.getUid());
            addChat(chatUid, danisan.getAdiSoyadi(), userRecord.getUid());

            System.out.println("Update time : " + future.get().getUpdateTime());
            key = true;
        } catch (Exception e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
        return key;
    }

    public Boolean updateDanisanWeight(String uid, String weight) {
        key = false;
        try {
            System.out.println("uid" + uid);
            DocumentReference docRef = db.collection("users").document(uid);
            Map<String, Object> updates = new HashMap<>();
            updates.put("weight", weight);
            ApiFuture<WriteResult> future = docRef.update(updates);
            System.out.println("Update time : " + future.get().getUpdateTime());
            key = true;
        } catch (Exception e) {
            System.out.println("Error updating weight: " + e.getMessage());
        }
        return key;
    }

    public Boolean addEaten(String eatenUid, String eatenName, String userID) {
        key = false;
        Map<String, Object> doceaten;
        doceaten = new HashMap<>();
        doceaten.put("eatenId", eatenUid);
        doceaten.put("eatenName", eatenName);
        doceaten.put("userId", userID);
        doceaten.put("recentEaten", "");


        String speakersUid = UUID.randomUUID().toString();

        List<String> speakers = new ArrayList<>();
        speakers.add(speakersUid);
        doceaten.put("speakers", speakers);

        try {
            ApiFuture<WriteResult> future = db.collection("eaten").document(eatenUid).set(doceaten);
            key = true;
            System.out.println("Update time : " + future.get().getUpdateTime());

        } catch (Exception e) {
            System.out.println("Error creating eaten: " + e.getMessage());
        }
        return key;
    }

    public Boolean addChat(String chatId, String chatName, String userID) {
        key = false;
        Map<String, Object> docChat;
        docChat = new HashMap<>();
        docChat.put("admin", userID);
        docChat.put("chatId", chatId);
        docChat.put("chatName", chatName);
        docChat.put("recentMessage", "");
        docChat.put("recentMessageSender", "");


        String speakersUid = UUID.randomUUID().toString();

        ArrayList<String> speakers = new ArrayList<>();
        speakers.add(speakersUid);
        docChat.put("members", speakers);

        try {
            ApiFuture<WriteResult> future = db.collection("chats").document(chatId).set(docChat);
            key = true;
            System.out.println("Update time : " + future.get().getUpdateTime());

        } catch (Exception e) {
            System.out.println("Error creating chats: " + e.getMessage());
        }
        return key;
    }


    public String getDocumentIdByFullName(String fullName) {

        CollectionReference usersRef = db.collection("users");
        Query query = usersRef.whereEqualTo("fullName", fullName);

        try {
            QuerySnapshot querySnapshot = query.get().get();
            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                String documentId = document.getId();
                return documentId;
            }
        } catch (Exception e) {
            System.out.println("Error retrieving document ID: " + e.getMessage());
        }

        return null;
    }

    public String getFullNameByDocumentId(String documentId) {
        String fullName = "";

        try {
            // Belirtilen belge kimliğiyle belgeyi sorgula
            DocumentReference docRef = db.collection("users").document(documentId);
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                // Danışanın fullName alanını al
                fullName = document.getString("fullName");
            } else {
                System.out.println("Belge bulunamadı: " + documentId);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return fullName;
    }

    public boolean deleteDanisanByFullName(String fullName) {
        try {
            // FullName alanı "aleyna" olan belgeleri sorgula
            Query query = db.collection("users").whereEqualTo("fullName", fullName);

            // Sorguyu çalıştır ve belgeleri al
            ApiFuture<QuerySnapshot> future = query.get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            for (QueryDocumentSnapshot document : documents) {
                // Belge referansını al ve belgeyi sil
                String documentId = document.getId();
                document.getReference().delete();
                System.out.println("Belge silindi: " + documentId);

                // Kullanıcıyı Firebase Authentication'dan sil
                String uid = document.getString("uid");
                if (uid != null) {
                    FirebaseAuth.getInstance().deleteUser(uid);
                    System.out.println("Kullanıcı başarıyla silindi: " + uid);
                } else {
                    System.out.println("Kullanıcı UID'si bulunamadı.");
                }
            }

            return true; // Belge ve kullanıcı başarıyla silindi
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Belge ve kullanıcı silme işlemi başarısız oldu
        }
    }

    public boolean deleteChatsByID(String uid) {
        try {
            Query query = db.collection("chats").whereEqualTo("admin", uid);

            ApiFuture<QuerySnapshot> future = query.get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            for (QueryDocumentSnapshot document : documents) {
                // Belge referansını al ve belgeyi sil
                String documentId = document.getId();
                document.getReference().delete();
                System.out.println("Belge silindi: " + documentId);
            }
            return true; // Belge ve kullanıcı başarıyla silindi
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Belge ve kullanıcı silme işlemi başarısız oldu
        }
    }

    public boolean deleteEatenByID(String uid) {
        try {
            Query query = db.collection("eaten").whereEqualTo("userId", uid);

            ApiFuture<QuerySnapshot> future = query.get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            for (QueryDocumentSnapshot document : documents) {
                // Belge referansını al ve belgeyi sil
                String documentId = document.getId();
                document.getReference().delete();
                System.out.println("Belge silindi: " + documentId);
            }
            return true; // Belge ve kullanıcı başarıyla silindi
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Belge ve kullanıcı silme işlemi başarısız oldu
        }
    }

    public boolean deleteDateByID(String uid) {
        try {
            Query query = db.collection("date").whereEqualTo("uid", uid);

            ApiFuture<QuerySnapshot> future = query.get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            for (QueryDocumentSnapshot document : documents) {
                // Belge referansını al ve belgeyi sil
                String documentId = document.getId();
                document.getReference().delete();
                System.out.println("Belge silindi: " + documentId);
            }
            return true; // Belge ve kullanıcı başarıyla silindi
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Belge ve kullanıcı silme işlemi başarısız oldu
        }
    }

    public boolean deleteWeightTrackingByDocumentId(String documentId) {
        try {
            DocumentReference docRef = db.collection("weightTracking").document(documentId);

            ApiFuture<WriteResult> future = docRef.delete();
            WriteResult result = future.get();

            System.out.println("Doküman başarıyla silindi. Son güncelleme zamanı: " + result.getUpdateTime());

            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }


    public Boolean deleteTarifByRecipeName(String recipeName) {
        key = false;
        // Asynchronously retrieve documents with the specified recipeName
        ApiFuture<QuerySnapshot> future = db.collection("recipe")
                .whereEqualTo("recipeName", recipeName)
                .get();

        try {
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                // Delete each document
                document.getReference().delete();
                System.out.println("Tarif silindi: " + document.getId());
                key = true;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return key;
    }

    public Boolean addDietician(Dietician dietician) {
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

    public Boolean addMotivasyon(String motivation) {
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

    public String readMotivasyon() {

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

    public Boolean addTarif(Tarif tarif) {
        key = false;
        Map<String, Object> docTarif;
        docTarif = new HashMap<>();
        docTarif.put("recipeName", tarif.getTarifAdi());
        docTarif.put("ingredients", tarif.getTarifIcerik());
        docTarif.put("recipeSteps", tarif.getTarifAdim());
        docTarif.put("recipeImages", "");
        docTarif.put("calories", tarif.getTarifKalorisi());
        try {
            ApiFuture<WriteResult> future = db.collection("recipe").document(UUID.randomUUID().toString()).set(docTarif);

            System.out.println("Update time : " + future.get().getUpdateTime());
            key = true;
        } catch (Exception e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
        return key;

    }

    /*
        public void getFoods(String title) throws ExecutionException, InterruptedException {
            CollectionReference foodsCollection = db.collection("foods");
            Query query = foodsCollection.whereEqualTo("name", title);

            ApiFuture<QuerySnapshot> querySnapshot = query.get();
            QuerySnapshot snapshot = querySnapshot.get();
            List<QueryDocumentSnapshot> documents = snapshot.getDocuments();

            List<Map<String, Object>> dataList = new ArrayList<>();
            for (QueryDocumentSnapshot document : documents) {
                Map<String, Object> data = document.getData();
                dataList.add(data);
            }

            System.out.println("Database service get: foods/breakfast datalist: " + dataList.get(0));
        }

        public boolean uploadImageToFirebaseStorage(String filePath, String fileName) {
            try {
                InputStream serviceAccount = new ByteArrayInputStream(DatabaseCredentialsFirebase.SEVICECONNECTION.getVALUE().getBytes(StandardCharsets.UTF_8));

                Storage storage = StorageOptions.newBuilder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build()
                        .getService();

                BlobId blobId = BlobId.of("diyet-dcb4e.appspot.com", "recipeImages" + fileName);
                BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

                storage.create(blobInfo, Files.readAllBytes(Paths.get(filePath)));

                return true; // Yükleme işlemi başarılı
            } catch (Exception e) {
                e.printStackTrace();
                return false; // Yükleme işlemi başarısız oldu
            }
        }

*/
    public Boolean loginDietician(Dietician dietician) {
        key = false;


        UserRecord userRecord = null;
        try {

            userRecord = FirebaseAuth.getInstance().getUserByEmail(dietician.getEmail().toString());
            // See the UserRecord reference doc for the contents of userRecord.
            DocumentReference docRef = db.collection("dietician").document(userRecord.getUid());
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                String email = document.getString("email");
                String sifre = document.getString("sifre");
                if (email != null && email.equals(userRecord.getEmail()) && email.equals(dietician.getEmail())) {
                    if (sifre.equals(dietician.getSifre())) {
                        key = true;
                    }
                }
            }
            System.out.println("Successfully fetched user data: " + userRecord.getUid() + " " + userRecord.getEmail());
        } catch (ExecutionException | InterruptedException | FirebaseAuthException e) {
            e.printStackTrace();
        }

        return key;
    }

    public boolean printMapNamesInDocument(String documentId, ObservableList<String> mapname) {
        key = false;
        try {
            DocumentReference docRef = db.collection("foods").document(documentId);
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot documentSnapshot = future.get();

            if (documentSnapshot.exists()) {
                for (String fieldName : documentSnapshot.getData().keySet()) {
                    Object fieldValue = documentSnapshot.get(fieldName);
                    if (fieldValue instanceof Map) {
                        System.out.println(fieldName);
                        mapname.add(fieldName);
                        key = true;
                    }
                }
            } else {
                System.out.println("Belge bulunamadı.");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return key;
    }

    // burası verilen map isminin altındaki tüm fielsları ekrana yazdırır
    public Boolean printFieldsInMap(ObservableList<String> fieldNames, String mapName) {
        try {
            ApiFuture<QuerySnapshot> future = db.collection("foods").get();
            QuerySnapshot querySnapshot = future.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

            for (QueryDocumentSnapshot document : documents) {
                Map<String, Object> map = document.getData();
                if (map.containsKey(mapName)) {
                    Map<String, Object> innerMap = (Map<String, Object>) map.get(mapName);
                    System.out.println("Belge ID: " + document.getId());
                    System.out.println("Harita İsmi: " + mapName);
                    System.out.println("Alanlar:");
                    for (Map.Entry<String, Object> entry : innerMap.entrySet()) {
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                        fieldNames.add(entry.getKey());
                    }
                    System.out.println("----------------------");
                }
            }

            if (documents.isEmpty()) {
                System.out.println("Koleksiyon boş.");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return true;
    }

    public String printFieldValueInMap(String fieldName, String mapname) {
        try {
            ApiFuture<QuerySnapshot> future = db.collection("foods").get();
            QuerySnapshot querySnapshot = future.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

            for (QueryDocumentSnapshot document : documents) {
                if (document.contains(mapname)) {
                    Map<String, Object> cerealMap = (Map<String, Object>) document.get(mapname);
                    if (cerealMap.containsKey(fieldName)) {
                        Object fieldValue = cerealMap.get(fieldName);
                        String fieldValueString = String.valueOf(fieldValue);
                        System.out.println("Belge ID: " + document.getId());
                        System.out.println("Harita İsmi: cereal");
                        System.out.println(fieldName + ": " + fieldValueString);
                        System.out.println("----------------------");
                        return fieldValueString;
                    }
                }
            }

            if (documents.isEmpty()) {
                System.out.println("Koleksiyon boş.");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*public Boolean addTarif(Tarif tarif) {
        key = false;
        Map<String, Object> docTarif;
        docTarif = new HashMap<>();
        docTarif.put("recipeName", tarif.getTarifAdi());
        docTarif.put("ingredients", tarif.getTarifIcerik());
        docTarif.put("recipeSteps", tarif.getTarifAdim());
        docTarif.put("recipeImages", "");
        docTarif.put("calories", tarif.getTarifKalorisi());
        try {
            ApiFuture<WriteResult> future = db.collection("recipe").document(UUID.randomUUID().toString()).set(docTarif);

            System.out.println("Update time : " + future.get().getUpdateTime());
            key = true;
        } catch (Exception e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
        return key;

    }*/
/*
    public boolean veriEkleDocument(String documentName, String uid, String[][] dizi) {
        try {
            // Diyet listesi koleksiyonu referansı
            CollectionReference diyetListRef = db.collection("diyetList");

            // Eklenecek alanları tanımlayın
            Map<String, Object> diyetData = new HashMap<>();

            // Diyet listesine UID alanını ekleyin
            diyetData.put("uid", uid);

            // İç içe koleksiyon referansı
            DocumentReference pazartesiRef = diyetListRef.document(UUID.randomUUID().toString()).collection("List").document(documentName);

            // Eklenecek alanları tanımlayın
            Map<String, Object> pazartesiData = new HashMap<>();

            // Alanları içeren bir Map oluşturun
            Map<String, Object> alanlar = new HashMap<>();
            for (int i = 0; i < dizi.length; i++) {
                String anahtar = dizi[i][0];
                String deger = dizi[i][1];
                alanlar.put(anahtar, deger);
            }

            // Pazartesi belgesine alanları ekleyin
            pazartesiData.put("alanlar", alanlar);

            // Belgeye alanları ekleyin
            ApiFuture<WriteResult> yazmaSonucu = pazartesiRef.set(pazartesiData);

            // İşlem tamamlandığında kontrol edin
            if (yazmaSonucu.get().getUpdateTime() != null) {
                return true; // İşlem başarılı oldu
            } else {
                return false; // İşlem başarısız oldu
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false; // Hata oluştu
        }
    }
*/
    public boolean veriEkleDocument(String documentName, String uid, String[][] dizi, String Day, String ogun) {
        try {
            // Diyet listesi koleksiyonu referansı
            CollectionReference diyetListRef = db.collection("diyetList");

            // Sorgu oluşturun
            Query sorgu = diyetListRef.whereEqualTo("uid", uid);

            // Sorguyu çalıştırın
            QuerySnapshot snapshot = sorgu.get().get();

            // Sonucu kontrol edin
            if (!snapshot.isEmpty()) {
                // Belge varsa güncelleme yap
                DocumentReference belgeRef = snapshot.getDocuments().get(0).getReference();
                DocumentReference pazartesiRef = belgeRef.collection("List").document(documentName);

                // Alanları içeren bir Map oluşturun
                Map<String, Object> alanlar = new HashMap<>();
                for (int i = 0; i < dizi.length; i++) {
                    String anahtar = dizi[i][0];
                    String deger = dizi[i][1];
                    alanlar.put(anahtar, deger);
                }

                // Önceden kaydedilmiş olan alanları alın
                Map<String, Object> oncekiAlanlar = (Map<String, Object>) snapshot.getDocuments().get(0).get("List." + documentName + "." + ogun);

                // Önceden kaydedilmiş alanları güncel alanlara ekle
                if (oncekiAlanlar != null) {
                    alanlar.putAll(oncekiAlanlar);
                }

                // Pazartesi belgesine güncellenmiş alanları ekleyin
                pazartesiRef.update(ogun, alanlar);
                pazartesiRef.update("day", Day);

                return true; // İşlem başarılı oldu
            } else {
                // Eklenecek alanları tanımlayın
                Map<String, Object> diyetData = new HashMap<>();

                // Diyet listesine UID alanını ekleyin
                diyetData.put("uid", uid);

                String documentId = UUID.randomUUID().toString();

                db.collection("diyetList").document(documentId).set(diyetData);

                // İç içe koleksiyon referansı
                DocumentReference pazartesiRef = diyetListRef.document(documentId).collection("List").document(documentName);

                Map<String, Object> adetogun = new HashMap<>();


                // Alanları içeren bir Map oluşturun
                Map<String, Object> alanlar = new HashMap<>();
                for (int i = 0; i < dizi.length; i++) {
                    String anahtar = dizi[i][0];
                    String deger = dizi[i][1];
                    alanlar.put(anahtar, deger);
                }
                adetogun.put(ogun, alanlar);
                // Pazartesi belgesine alanları ekleyin
                pazartesiRef.set(adetogun);
                pazartesiRef.update("day", Day);

                String[] gunler = {"Pazartesi", "Sali", "Carsamba", "Persembe", "Cuma", "Cumartesi", "Pazar"};
                for (String gun : gunler) {
                    if (gun != documentName)
                        diyetListRef.document(documentId).collection("List").document(gun).set(new HashMap<>());
                }

                return true; // İşlem başarılı oldu

            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false; // Hata oluştu
        }
    }

    public Boolean veriCekDiyetList1(ObservableList<String> list, String day, String uid) {
        key = false;
        try {
            // Diyet listesi koleksiyonu referansı
            CollectionReference diyetListRef = db.collection("diyetList");

            // Diyet listesindeki belgeleri sorgula
            Query query = diyetListRef.whereEqualTo("uid", uid);
            ApiFuture<QuerySnapshot> querySnapshot = query.get();

            // Sorgu sonuçlarını alın ve işleyin
            QuerySnapshot snapshot = querySnapshot.get();
            if (!snapshot.isEmpty()) {
                for (DocumentSnapshot document : snapshot.getDocuments()) {
                    // İç içe koleksiyon referansı
                    DocumentReference pazartesiRef = document.getReference().collection("List").document(day);

                    // Pazartesi belgesini getirin
                    ApiFuture<DocumentSnapshot> gelecekVeri = pazartesiRef.get();

                    // Veriyi alın ve yazdırın
                    DocumentSnapshot veriSnapshot = gelecekVeri.get();
                    if (veriSnapshot.exists()) {
                        Map<String, Object> veri = veriSnapshot.getData();
                        if (veri != null) {
                            for (String key : veri.keySet()) {
                                if (!key.equals("calories")) {
                                    if (!key.equals("day")) {
                                        System.out.println(">>" + ogunAdi(key) + "<<");
                                        list.add(String.valueOf(">>" + ogunAdi(key) + "<<"));
                                        Map<String, Object> lunchMap = (Map<String, Object>) veri.get(key);
                                        if (lunchMap != null) {
                                            for (Map.Entry<String, Object> entry : lunchMap.entrySet()) {
                                                list.add(hesaplaVeYazdir(String.valueOf(entry.getKey()), String.valueOf(entry.getValue())));
                                                System.out.println(hesaplaVeYazdir(String.valueOf(entry.getKey()), String.valueOf(entry.getValue())));
                                                this.key = true;
                                            }
                                        } else {
                                            System.out.println("Lunch Map bulunamadı.");
                                        }
                                        list.add(" ");
                                    }
                                }
                            }
                        }
                    } else {
                        System.out.println("Veri bulunamadı.");
                    }
                }
            } else {
                System.out.println("Belge bulunamadı.");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return key;
    }

    public String hesaplaVeYazdir(String str, String sayiStr) {
        // İlk stringi "x" karakterine göre ayırın
        String[] parts = str.split("x", 2);
        String metin = parts[0].trim();
        String tane = parts[1].trim();

        // İkinci stringi sayıya dönüştürün
        int sayi = Integer.parseInt(sayiStr);

        // Sonuç hesaplaması
        int sonuc = sayi * Integer.parseInt(tane);

        // Çıktıyı oluşturun
        String output = metin + " " + tane + " tane -> " + sonuc;

        return output;
    }

    public String ogunAdi(String ogunAdi) {
        if (ogunAdi.equals("breakfast"))
            return "Kahvaltı";
        else if (ogunAdi.equals("snack"))
            return "Araöğün1";
        else if (ogunAdi.equals("lunch"))
            return "Öğle";
        else if (ogunAdi.equals("snack2"))
            return "Araöğün2";
        else if (ogunAdi.equals("dinner"))
            return "Akşam";
        return "Araöğün3";
    }


    public Boolean getAllChatNames(ObservableList<String> chatNames) {
        key = false;
        CollectionReference chatsCollection = db.collection("chats");

        try {
            QuerySnapshot querySnapshot = chatsCollection.get().get();
            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                if (document.exists()) {
                    String chatName = document.getString("chatName");
                    if (chatName != null) {
                        chatNames.add(chatName);
                        key = true;
                    }
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return key;
    }

    public boolean readChatsByChatName(ObservableList<Chat> chats, String chatName) {
        key = false;
        try {
            Firestore firestore = FirestoreClient.getFirestore();
            CollectionReference chatsCollection = firestore.collection("chats");

            // Sorguyu oluştur
            Query query = chatsCollection.whereEqualTo("chatName", chatName);

            // Sorguyu çalıştır
            ApiFuture<QuerySnapshot> querySnapshot = query.get();

            // Belge snapshot'larını al
            QuerySnapshot snapshot = querySnapshot.get();


            // Her belge için döngü
            for (QueryDocumentSnapshot document : snapshot) {
                CollectionReference messagesCollection = document.getReference().collection("messages");
                ApiFuture<QuerySnapshot> messagesQuerySnapshot = messagesCollection.get();
                QuerySnapshot messagesSnapshot = messagesQuerySnapshot.get();

                // Her mesaj için döngü
                for (QueryDocumentSnapshot messageDocument : messagesSnapshot) {
                    String message = messageDocument.get("message").toString();
                    String time = messageDocument.get("time").toString();
                    String sender = messageDocument.get("sender").toString();

                    Chat chat = new Chat(message, sender, time);
                    chats.add(chat);
                    key = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return key;
    }

    public void sendChatMessage(String chatName, Chat chat) {
        try {
            CollectionReference chatsCollection = db.collection("chats");

            // Sorguyu oluştur
            Query query = chatsCollection.whereEqualTo("chatName", chatName);

            // Sorguyu çalıştır
            ApiFuture<QuerySnapshot> querySnapshot = query.get();

            // Belge snapshot'larını al
            QuerySnapshot snapshot = querySnapshot.get();

            // Her belge için döngü
            for (QueryDocumentSnapshot document : snapshot) {
                CollectionReference messagesCollection = document.getReference().collection("messages");

                DocumentReference messageDocumentRef = messagesCollection.document(); // Random belge oluştur

                Map<String, Object> docChat = new HashMap<>();
                docChat.put("message", chat.getMessage());
                docChat.put("sender", chat.getSender());
                docChat.put("time", chat.getTime());

                messageDocumentRef.set(docChat); // Alanları belgeye ekle
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean getEatenNames(String day, ObservableList<String> eatenNames) {

        key = false;
        try {
            CollectionReference eatenCollection = db.collection("eaten");

            QuerySnapshot eatenSnapshot = eatenCollection.get().get();
            List<QueryDocumentSnapshot> eatenDocuments = eatenSnapshot.getDocuments();

            for (QueryDocumentSnapshot eatenDocument : eatenDocuments) {
                CollectionReference eatensCollection = eatenDocument.getReference().collection("eatens");
                Query eatensQuery = eatensCollection.whereEqualTo("day", day);

                ApiFuture<QuerySnapshot> eatensSnapshotFuture = eatensQuery.get();
                QuerySnapshot eatensSnapshot = eatensSnapshotFuture.get();
                List<QueryDocumentSnapshot> eatensDocuments = eatensSnapshot.getDocuments();

                for (QueryDocumentSnapshot eatensDocument : eatensDocuments) {
                    String eatenName = eatensDocument.getString("sender");
                    System.out.println(eatenName);
                    eatenNames.add(eatenName);
                    key = true;
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            // Handle the exception or log an error
            e.printStackTrace();
        }

        return key;
    }

    //String day, List<String> eatenArray
    public List<Eaten> getEatenData(List<Eaten> eatenArray) {
        try {
            CollectionReference eatenCollection = db.collection("eaten");
            QuerySnapshot eatenSnapshot = eatenCollection.get().get();
            List<QueryDocumentSnapshot> eatenDocuments = eatenSnapshot.getDocuments();

            for (QueryDocumentSnapshot eatenDocument : eatenDocuments) {
                CollectionReference eatensCollection = eatenDocument.getReference().collection("eatens");
                Query eatensQuery = eatensCollection.whereEqualTo("day", "13").whereEqualTo("sender", "Ceylan Doğan");

                ApiFuture<QuerySnapshot> eatensSnapshotFuture = eatensQuery.get();
                QuerySnapshot eatensSnapshot = eatensSnapshotFuture.get();
                List<QueryDocumentSnapshot> eatensDocuments = eatensSnapshot.getDocuments();

                for (QueryDocumentSnapshot eatensDocument : eatensDocuments) {
                    Eaten eaten = new Eaten(); // Yeni Eaten nesnesi oluştur

                    for (String field : eatensDocument.getData().keySet()) {
                        Object value = eatensDocument.get(field);
                        if (field.equals("ogunTime"))
                            eaten.setOguntime(String.valueOf(value));
                        else if (field.equals("totalCalorie"))
                            eaten.setCalories(String.valueOf(value));
                        else if (field.equals("eaten"))
                            eaten.setEaten(String.valueOf(value));
                        System.out.println(field + ": " + value);
                    }
                    System.out.println("-----");

                    eatenArray.add(eaten); // Yeni nesneyi eatenArray'e ekle
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return eatenArray;
    }
}