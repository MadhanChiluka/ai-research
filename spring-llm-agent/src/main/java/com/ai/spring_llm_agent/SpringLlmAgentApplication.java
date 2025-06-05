package com.ai.spring_llm_agent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.CommandScan;

@SpringBootApplication(exclude = {
		org.springframework.cloud.function.context.config.ContextFunctionCatalogAutoConfiguration.class
})
@CommandScan
public class SpringLlmAgentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringLlmAgentApplication.class, args);
	}

}
