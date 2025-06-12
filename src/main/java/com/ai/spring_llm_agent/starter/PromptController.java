package com.ai.spring_llm_agent.starter;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prompt")
public class PromptController {

    private final ChatClient chatClient;

    public PromptController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @RequestMapping("/dad-jokes")
    public String generate() {
        /*
         * return chatClient.call(new
         * Prompt("Tell me a dad joke")).getResult().getOutput().getContent();
         */
        return chatClient.prompt()
                .user(u -> u.text("Tell me a dad joke")).call().content();
    }
}