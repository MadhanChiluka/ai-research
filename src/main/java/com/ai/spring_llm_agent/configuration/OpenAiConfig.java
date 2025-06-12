package com.ai.spring_llm_agent.configuration;

import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.OpenAiEmbeddingModel;

@Configuration
@Profile("openai")
public class OpenAiConfig {

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @Value("${spring.ai.openai.chat.options.model:gpt-4}")
    private String model;

    @Bean
    public ChatClient.Builder chatClientBuilder() {
        OpenAiApi api = new OpenAiApi(apiKey);
        OpenAiChatOptions options = OpenAiChatOptions.builder().withModel(model).build();
        ChatModel chatModel = new OpenAiChatModel(api, options);
        return ChatClient.builder(chatModel);
    }

    @Bean
    public EmbeddingModel openAiEmbeddingModel(
            @Value("${spring.ai.openai.api-key}") String apiKey) {
        OpenAiApi api = new OpenAiApi(apiKey);
        return new OpenAiEmbeddingModel(api);
    }
}
