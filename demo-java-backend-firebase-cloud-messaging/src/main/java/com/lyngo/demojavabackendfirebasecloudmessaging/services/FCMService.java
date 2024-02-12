package com.lyngo.demojavabackendfirebasecloudmessaging.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;

@Service
public class FCMService {
    private final FirebaseMessaging firebaseMessaging;
    

    public FCMService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    public void sendMessage(String token){
        try{
            Message message = Message.builder()
                    .setNotification(Notification.builder()
                        .setBody("Backend firebase notification")
                        .setTitle("Title")
                        .build())
                    .setToken(token)
                    .build();
            String response = firebaseMessaging.send(message);
            System.out.println(response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendMessageToMultipleUser(List<String> tokens){
        try{
            MulticastMessage message = MulticastMessage.builder()
                    .putData("key1", "value1")
                    .addAllTokens(tokens)
                    .build();
            BatchResponse response = firebaseMessaging.sendEachForMulticast(message);
            response.getResponses().forEach(sendResponse -> {
                System.out.println(sendResponse.getMessageId() + sendResponse.isSuccessful());
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
