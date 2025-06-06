package com.ai.spring_llm_agent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.shell.command.annotation.CommandScan;

import com.ai.spring_llm_agent.functions.WeatherConfigProperties;

@SpringBootApplication(exclude = {
		org.springframework.cloud.function.context.config.ContextFunctionCatalogAutoConfiguration.class
})
@CommandScan
@EnableConfigurationProperties(WeatherConfigProperties.class)
public class SpringLlmAgentApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringLlmAgentApplication.class, args);
	}

}
