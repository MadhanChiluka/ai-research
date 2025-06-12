package com.ai.spring_llm_agent.contextstuffing;

import java.io.IOException;
import java.nio.charset.Charset;

import org.jline.utils.Log;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("olympics")
public class OlympicController {

    private final ChatClient chatClient;

    public OlympicController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Value("classpath:/prompts/olympic-sports.st")
    private Resource olympicsSportsResource;

    @Value("classpath:/docs/olympic-sports.txt")
    private Resource docsToStuffResource;

    @GetMapping("/2024")
    public String get2024OlympicSports(
            @RequestParam(value = "message", defaultValue = "What sports are being included in 2024 summer olympics") String message,
            @RequestParam(value = "stuffit", defaultValue = "false") boolean stuffit) throws IOException {
        /*
         * PromptTemplate promptTemplate = new PromptTemplate(olympicsSportsResource);
         * Map<String, Object> map = new HashMap<>();
         * map.put("question", message);
         * map.put("context", stuffit == false ? "" : docsToStuffResource);
         * Prompt prompt = promptTemplate.create(map);
         * ChatResponse response = chatClient.call(prompt);
         * return response.getResult().getOutput().getContent();
         */
        String sports = docsToStuffResource.getContentAsString(Charset.defaultCharset());
        Log.info("Sports: {}", sports);
        return chatClient.prompt()
                .user(u -> {
                    u.text(olympicsSportsResource);
                    u.param("question", message);
                    u.param("context", stuffit ? sports : "");
                })
                .call()
                .content();

    }

}