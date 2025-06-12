package com.ai.spring_llm_agent.starter;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DadJokeController {

    private final ChatClient chatClient;

    public DadJokeController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/jokes")
    public String generate() {
        /*
         * var user = new UserMessage("Tell me a joke about cats");
         * Prompt prompt = new Prompt(user);
         * return chatClient.call(prompt).getResult().getOutput().getContent();
         */
        return chatClient.prompt().user(u -> u.text("Tell me a joke about cats")).call().content();
    }

    @GetMapping("/system-context/jokes")
    public String generateSystemContext() {

        String systemMessage = "Your primary function is to tell Dad Jokes. If someone asks you to tell another type of joke, politely tell them you only know datd jokes";
        String userMessage = "Tell me a serious joke about the universe";

        /*
         * var system = new SystemMessage(
         * systemMessage);
         * var user = new UserMessage("Tell me a serious joke about the universe");
         * Prompt prompt = new Prompt(List.of(system, user));
         * return chatClient.call(prompt).getResult().getOutput().getContent()
         */;

        return chatClient.prompt()
                .system(systemMessage).user(u -> u.text(userMessage)).call().content();
    }

}
