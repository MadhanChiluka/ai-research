package com.ai.spring_llm_agent.streaming;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@CrossOrigin
public class StreamChatController {

    private final ChatClient chatClient;

    public StreamChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        return chatClient.prompt()
                .user(message).call().content();
    }

    @GetMapping("/stream-chat")
    public Flux<String> postMethodName(@RequestParam String message) {
        return chatClient.prompt()
                .user(message).stream().content();
    }

}
