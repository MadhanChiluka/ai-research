package com.ai.spring_llm_agent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		org.springframework.cloud.function.context.config.ContextFunctionCatalogAutoConfiguration.class
})
public class SpringLlmAgentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringLlmAgentApplication.class, args);
	}

}
