package com.ai.spring_llm_agent.docsassistant;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.core.io.Resource;
import org.springframework.shell.command.annotation.Command;

@Command
public class SpringAssistantCommand {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    @Value("classpath:/prompts/spring-boot-reference.st")
    private Resource sbPromptTemplate;

    public SpringAssistantCommand(ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
        this.chatClient = chatClientBuilder.build();
        this.vectorStore = vectorStore;
    }

    @Command(command = "q")
    public String question(@DefaultValue(value = "What is Spring Boot") String message) {
        /*
         * PromptTemplate promptTemplate = new PromptTemplate(sbPromptTemplate);
         * Map<String, Object> map = new HashMap<>();
         * map.put("input", message);
         * map.put("documents", String.join("\n", findSimilarDocuments(message)));
         * Prompt prompt = promptTemplate.create(map);
         * return chatClient.call(prompt).getResult().getOutput().getContent();
         */
        return chatClient.prompt()
                .user(u -> u.text(sbPromptTemplate)
                        .param("input", message)
                        .param("documents", String.join("\n", findSimilarDocuments(message))))
                .call()
                .content();

    }

    private List<String> findSimilarDocuments(String message) {
        List<Document> similarDocuments = vectorStore.similaritySearch(SearchRequest.query(message).withTopK(3));
        return similarDocuments.stream().map(Document::getContent).toList();
    }

}
