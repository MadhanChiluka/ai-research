package com.ai.spring_mind;

import java.util.Map;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/youtube")
public class YoutubeController {

    @Autowired
    private ChatClient chatClient;

    @Value("classpath:/prompts/youtube.st")
    private Resource resource;

    @GetMapping("/popular")
    public String findPopularYoutuberByGenre(@RequestParam(value = "genre", defaultValue = "tech") String genre) {
        PromptTemplate promptTemplate = new PromptTemplate(resource);
        Prompt prompt = promptTemplate.create(Map.of("genre", genre));
        return chatClient.call(prompt).getResult().getOutput().getContent();
    }
}