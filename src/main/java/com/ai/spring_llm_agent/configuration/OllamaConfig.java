package com.ai.spring_llm_agent.configuration;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.OllamaEmbeddingModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("ollama")
public class OllamaConfig {

    @Value("${spring.ai.ollama.base-url:http://localhost:11434}")
    private String baseUrl;

    @Value("${spring.ai.ollama.chat.options.model:llama3.2}")
    private String model;

    @Bean
    public ChatClient.Builder chatClientBuilder() {
        OllamaOptions options = OllamaOptions.create().withModel(model);
        ChatModel chatModel = new OllamaChatModel(new OllamaApi(baseUrl), options);
        return ChatClient.builder(chatModel);
    }

    @Bean
    public EmbeddingModel ollamaEmbeddingModel() {
        return new OllamaEmbeddingModel(new OllamaApi(baseUrl));
    }
}
