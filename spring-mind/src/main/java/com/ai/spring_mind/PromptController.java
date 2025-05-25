package com.ai.spring_mind;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prompt")
public class PromptController {

    @Autowired
    private ChatClient chatClient;

    @RequestMapping("/dad-jokes")
    public String generate() {
        return chatClient.call(new Prompt("Tell me a dad joke")).getResult().getOutput().getContent();
    }
}