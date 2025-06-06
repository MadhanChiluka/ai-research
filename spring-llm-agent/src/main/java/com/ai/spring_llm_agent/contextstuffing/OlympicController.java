package com.ai.spring_llm_agent.contextstuffing;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("olympics")
public class OlympicController {

    private final ChatClient chatClient;

    public OlympicController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Value("classpath:/prompts/olympic-sports.st")
    private Resource olympicsSportsResource;

    @Value("classpath:/docs/olympic-sports.txt")
    private Resource docsToStuffResource;

    @GetMapping("/2024")
    public String get2024OlympicSports(
            @RequestParam(value = "message", defaultValue = "What sports are being included in 2024 summer olympics") String message,
            @RequestParam(value = "stuffit", defaultValue = "false") boolean stuffit) {
        PromptTemplate promptTemplate = new PromptTemplate(olympicsSportsResource);
        Map<String, Object> map = new HashMap<>();
        map.put("question", message);
        map.put("context", stuffit == false ? "" : docsToStuffResource);
        Prompt prompt = promptTemplate.create(map);
        ChatResponse response = chatClient.call(prompt);
        return response.getResult().getOutput().getContent();
    }

}