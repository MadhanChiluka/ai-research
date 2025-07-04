package com.ai.spring_llm_agent.contextstuffing;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// @RestController
public class RagController {

    private final ChatClient chatClient;
    // private final VectorStore vectorStore;

    @Value("classpath:/prompts/rag-prompt-template.st")
    private Resource ragPromptTemplate;

    public RagController(ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
        this.chatClient = chatClientBuilder
                .defaultAdvisors(new QuestionAnswerAdvisor(vectorStore, SearchRequest.defaults())).build();
    }

    @GetMapping("/faq")
    public String faq(
            @RequestParam(value = "message", defaultValue = "How can I buy tickets for the Olympic Games Paris 2024") String message) {
        /*
         * List<Document> similarDocuments =
         * vectorStore.similaritySearch(SearchRequest.query(message).withTopK(2));
         * List<String> contentList =
         * similarDocuments.stream().map(Document::getContent).toList();
         * PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);
         * Map<String, Object> promptParameters = new HashMap<>();
         * promptParameters.put("input", message);
         * promptParameters.put("documents", String.join("\n", contentList));
         * Prompt prompt = promptTemplate.create(promptParameters);
         * return chatClient.call(prompt).getResult().getOutput().getContent();
         */

        return chatClient.prompt().user(message).call().content();

    }

}
