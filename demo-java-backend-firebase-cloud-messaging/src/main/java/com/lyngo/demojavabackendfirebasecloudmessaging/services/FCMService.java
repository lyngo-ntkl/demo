package com.lyngo.demojavabackendfirebasecloudmessaging.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;

@Service
public class FCMService {
    private void init() throws IOException {
        GoogleCredentials credentials = getCredentials();
        FirebaseOptions options = FirebaseOptions.builder()
                // set google creadential to authorize to firebase service?
                .setCredentials(credentials)
                // set database url
                .setDatabaseUrl("https://fir-fcm-d683d-default-rtdb.firebaseio.com/")
                .build();
        FirebaseApp app = FirebaseApp.initializeApp(options);
    }

    private GoogleCredentials getCredentials() throws IOException {
        File file = new File("src//main//resources//service_account.json");
        FileInputStream stream = new FileInputStream(file);
        GoogleCredentials credentials = GoogleCredentials
                // get credentials
                .fromStream(stream)
                // this will authorize for fcm service
                .createScoped(Arrays.asList(new String[]{"https://www.googleapis.com/auth/firebase.messaging"}));
        return credentials;
    }

    public void sendMessage(String token){
        try{
            init();
            Message message = Message.builder()
                    .putData("key1", "value1")
                    .setToken(token)
                    .build();
            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println(response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendMessageToMultipleUser(List<String> tokens){
        try{
            init();
            MulticastMessage message = MulticastMessage.builder()
                    .putData("key1", "value1")
                    .addAllTokens(tokens)
                    .build();
            BatchResponse response = FirebaseMessaging.getInstance().sendEachForMulticast(message);
            response.getResponses().forEach(sendResponse -> {
                System.out.println(sendResponse.getMessageId() + sendResponse.isSuccessful());
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
