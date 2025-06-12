package com.ai.spring_llm_agent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.shell.command.annotation.CommandScan;

import com.ai.spring_llm_agent.functions.WeatherConfigProperties;

@SpringBootApplication(exclude = {
		org.springframework.cloud.function.context.config.ContextFunctionCatalogAutoConfiguration.class,
		org.springframework.ai.autoconfigure.openai.OpenAiAutoConfiguration.class,
		org.springframework.ai.autoconfigure.ollama.OllamaAutoConfiguration.class,
		org.springframework.ai.autoconfigure.chat.client.ChatClientAutoConfiguration.class,
		// org.springframework.ai.autoconfigure.embedding.EmbeddingAutoConfiguration.class
})
@CommandScan
@EnableConfigurationProperties(WeatherConfigProperties.class)
public class SpringLlmAgentApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringLlmAgentApplication.class, args);
	}

}
