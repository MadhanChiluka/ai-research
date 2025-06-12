package com.ai.spring_llm_agent.starter;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/youtube")
public class YoutubeController {

    private final ChatClient chatClient;

    public YoutubeController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Value("classpath:/prompts/youtube.st")
    private Resource resource;

    @GetMapping("/popular")
    public String findPopularYoutuberByGenre(@RequestParam(value = "genre", defaultValue = "tech") String genre) {
        /*
         * PromptTemplate promptTemplate = new PromptTemplate(resource);
         * Prompt prompt = promptTemplate.create(Map.of("genre", genre));
         * return chatClient.call(prompt).getResult().getOutput().getContent();
         */

        return chatClient.prompt()
                .user(u -> u.text(resource).param("genre", genre)).call().content();
    }
}