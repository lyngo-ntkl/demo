package com.lyngo.demojavabackendfirebasecloudmessaging.configurations;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;

@Configuration
public class FirebaseConfiguration {
    @Bean
    FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp){
        return FirebaseMessaging.getInstance(firebaseApp);
    }
    @Bean
    FirebaseApp firebaseApp(GoogleCredentials credentials) throws IOException{
        FirebaseOptions options = FirebaseOptions.builder()
            .setCredentials(credentials)
            .build();
        return FirebaseApp.initializeApp(options);
    }

    @Bean
    GoogleCredentials getCredentials() throws IOException {
        File file = new File("src//main//resources//service_account.json");
        FileInputStream stream = new FileInputStream(file);
        GoogleCredentials credentials = GoogleCredentials
                // get credentials
                .fromStream(stream)
                // this will authorize for fcm service
                .createScoped(Arrays.asList(new String[]{"https://www.googleapis.com/auth/firebase.messaging"}));
        return credentials;
    }
}
