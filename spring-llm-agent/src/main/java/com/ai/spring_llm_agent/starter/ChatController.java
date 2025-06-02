package com.ai.spring_llm_agent.starter;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.ai.chat.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ChatController {

    @Autowired
    private ChatClient chatClient;

    @GetMapping("/dad-jokes")
    public String generate(@RequestParam(value = "message", defaultValue = "Tell me a dad joke") String message) {
        return chatClient.call(message);

    }

}
