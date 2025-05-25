package com.ai.spring_mind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		org.springframework.cloud.function.context.config.ContextFunctionCatalogAutoConfiguration.class
})
public class SpringMindApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMindApplication.class, args);
	}

}
