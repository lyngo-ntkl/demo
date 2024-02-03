package com.lyngo.demojavabackendfirebasecloudmessaging.controllers;

import com.lyngo.demojavabackendfirebasecloudmessaging.services.FCMService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FCMController {
    private final FCMService fcmService;

    public FCMController(FCMService fcmService) {
        this.fcmService = fcmService;
    }

    @GetMapping(path = "/message/device/{id}")
    public void sendMessage(@PathVariable String id){
        fcmService.sendMessage(id);
    }

    @PostMapping(path = "/message/devices")
    public void sendMessages(@RequestBody List<String> tokens){
        fcmService.sendMessageToMultipleUser(tokens);
    }
}
