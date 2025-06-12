package com.ai.spring_llm_agent.outputparsers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class StreamController {

    private final ChatClient chatClient;

    public StreamController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping(value = "/stream")
    public Flux<String> stream() {
        return chatClient.prompt()
                .user("I am traveling to Kansas City next week what are 10 of the best BBQ joints in the city")
                .stream()
                .content();
    }

}
