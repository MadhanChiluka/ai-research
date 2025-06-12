package com.ai.spring_llm_agent.outputparsers;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// This file demonstrates example of ListOutputParser usage in a Spring AI application.
@RestController
public class SongsController {

    private final ChatClient chatClient;

    public SongsController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/songs")
    public String getSongsByArtist(@RequestParam(value = "artist", defaultValue = "Tayloar Swift") String artist) {
        var message = """
                Give me a list of top 10 songs for the artist {artist}.
                If you don't knwo, just say dont know";
                """;
        /*
         * PromptTemplate promptTemplate = new PromptTemplate(message, Map.of("artist",
         * artist));
         * Prompt prompt = promptTemplate.create();
         * return chatClient.call(prompt).getResult().getOutput().getContent();
         */

        return chatClient.prompt()
                .user(u -> u.text(message).param("artist", artist))
                .call()
                .content();
    }

    @GetMapping("/songs/v2")
    public List<String> getSongsByArtistUsingOutputParser(
            @RequestParam(value = "artist", defaultValue = "Taylor Swift") String artist) {
        var message = """
                Give me a list of top 10 songs for the artist {artist}.
                If you don't know, just say don't know.
                """;

        /*
         * var message = """
         * Give me a list of top 10 songs for the artist {artist}.
         * If you don't know, just say don't know. {format}
         * """;
         * ListOutputParser outputParser = new ListOutputParser(new
         * DefaultConversionService());
         * 
         * PromptTemplate promptTemplate = new PromptTemplate(message,
         * Map.of("artist", artist, "format", outputParser.getFormat()));
         * Prompt prompt = promptTemplate.create();
         * return
         * outputParser.parse(chatClient.call(prompt).getResult().getOutput().getContent
         * ());
         */

        return chatClient.prompt()
                .user(u -> u.text(message).param("artist", artist)).call().entity(List.class);
    }

}
