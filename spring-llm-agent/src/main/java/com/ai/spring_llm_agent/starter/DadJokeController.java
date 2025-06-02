package com.ai.spring_llm_agent.starter;

import java.util.List;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class DadJokeController {

    private final ChatClient chatClient;

    public DadJokeController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/jokes")
    public String generate() {
        var user = new UserMessage("Tell me a joke about cats");
        Prompt prompt = new Prompt(user);
        return chatClient.call(prompt).getResult().getOutput().getContent();
    }

    @GetMapping("/system-context/jokes")
    public String generateSystemContext() {
        var system = new SystemMessage(
                "Your primary function is to tell Dad Jokes. If someone asks you to tell another type of joke, politely tell them you only know datd jokes");
        var user = new UserMessage("Tell me a serious joke about the universe");
        Prompt prompt = new Prompt(List.of(system, user));
        return chatClient.call(prompt).getResult().getOutput().getContent();
    }

}
