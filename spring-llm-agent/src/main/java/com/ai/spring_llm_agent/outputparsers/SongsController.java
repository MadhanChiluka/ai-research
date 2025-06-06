package com.ai.spring_llm_agent.outputparsers;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.parser.ListOutputParser;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// This file demonstrates example of ListOutputParser usage in a Spring AI application.
@RestController
public class SongsController {

    private final ChatClient chatClient;

    public SongsController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/songs")
    public String getSongsByArtist(@RequestParam(value = "artist", defaultValue = "Tayloar Swift") String artist) {
        var message = """
                Give me a list of top 10 songs for the artist {artist}.
                If you don't knwo, just say dont know";
                """;
        PromptTemplate promptTemplate = new PromptTemplate(message, Map.of("artist", artist));
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatClient.call(prompt);
        return response.getResult().getOutput().getContent();
    }

    @GetMapping("/songs/v2")
    public List<String> getSongsByArtistUsingOutputParser(
            @RequestParam(value = "artist", defaultValue = "Taylor Swift") String artist) {
        var message = """
                Give me a list of top 10 songs for the artist {artist}.
                If you don't know, just say don't know. {format}
                """;

        ListOutputParser outputParser = new ListOutputParser(new DefaultConversionService());

        PromptTemplate promptTemplate = new PromptTemplate(message,
                Map.of("artist", artist, "format", outputParser.getFormat()));
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatClient.call(prompt);
        return outputParser.parse(response.getResult().getOutput().getContent());
    }

}
