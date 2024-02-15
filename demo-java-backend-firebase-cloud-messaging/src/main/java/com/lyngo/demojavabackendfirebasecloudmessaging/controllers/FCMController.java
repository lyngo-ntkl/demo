package com.lyngo.demojavabackendfirebasecloudmessaging.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lyngo.demojavabackendfirebasecloudmessaging.services.FCMService;

@RestController
public class FCMController {
    private final FCMService fcmService;

    public FCMController(FCMService fcmService) {
        this.fcmService = fcmService;
    }

    @PostMapping(path = "/message/device/{id}")
    public void sendMessage(@PathVariable String id){
        fcmService.sendMessage(id);
    }

    @PostMapping(path = "/message/devices")
    public void sendMessages(@RequestBody List<String> tokens){
        fcmService.sendMessageToMultipleUser(tokens);
    }

    @PostMapping(path = "/message/topic/{topic}")
    public void sendMessageToTopic(@PathVariable String topic) {
        fcmService.sendMessageToTopic(topic);
    }
    
    @PostMapping(path = "/message/topic/subscribe")
    public void subscribeTopic(@RequestParam(name = "topic") String topic, @RequestParam(name = "token") String token) {
        fcmService.subscribeTopic(topic, token);
    }
    
}
