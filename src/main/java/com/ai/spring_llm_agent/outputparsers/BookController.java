package com.ai.spring_llm_agent.outputparsers;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// This file demonstrates the example of MapOutputParser usage in a Spring AI application.
@RestController
@RequestMapping("/books")
public class BookController {
    private final ChatClient chatClient;

    public BookController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/by-author/{author}")
    public Author getBooksByAuthor(@PathVariable String author) {
        String promptMessage = """
                Generate list of books written by the author {author}. If you aren't positive
                that a book belongs to this author please don't include it.
                """;

        /*
         * String promptMessage = """
         * Generate list of books written by the author {author}. If you aren't positive
         * that a book belongs to this author please don't include it. {format}
         * """;
         * var outputParser = new BeanOutputParser<>(Author.class);
         * String format = outputParser.getFormat();
         * PromptTemplate promptTemplate = new PromptTemplate(promptMessage,
         * Map.of("author", author, "format", format));
         * Prompt prompt = promptTemplate.create();
         * String output = chatClient.call(prompt).getResult().getOutput().getContent();
         * return outputParser.parse(output);
         */

        return chatClient.prompt()
                .user(u -> u.text(promptMessage)
                        .param("author", author))
                .call()
                .entity(Author.class);
    }

    @GetMapping("author/{author}")
    public Map<String, Object> byAuthor(@PathVariable String author) {

        String promptMessage = """
                Give me a list of links by {author}.
                Include the author's name as key and any social network links as the objects.

                """;

        /*
         * String promptMessage = """
         * Give me a list of links by {author}.
         * Include the author's name as key and any social network links as the objects.
         * {format}
         * """;
         * MapOutputParser outputParser = new MapOutputParser();
         * String format = outputParser.getFormat();
         * PromptTemplate promptTemplate = new PromptTemplate(promptMessage,
         * Map.of("author", author, "format", format));
         * 
         * Prompt prompt = promptTemplate.create();
         * Generation generation = chatClient.call(prompt).getResult();
         * String rawOutput = generation.getOutput().getContent();
         * String cleanedOutput = stripCodeFences(rawOutput);
         * 
         * Map<String, Object> result = outputParser.parse(cleanedOutput);
         * 
         * return result;
         */

        return chatClient.prompt().user(u -> u.text(promptMessage).param("author", author)).call().entity(Map.class);
    }

    private String stripCodeFences(String text) {
        if (text == null)
            return "";

        // Remove ```json or ``` and ```
        return text
                .replaceAll("^```[a-zA-Z]*\\s*", "") // remove opening fence
                .replaceAll("\\s*```$", "") // remove closing fence
                .trim();
    }

}
