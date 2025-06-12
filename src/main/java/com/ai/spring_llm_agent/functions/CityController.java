package com.ai.spring_llm_agent.functions;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final ChatClient chatClient;
    private static final String systemMessageStr = "You are a helpful AI assistant answering questions about cities across the world";

    public CityController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping()
    public String getCityInfo(@RequestParam(value = "message") String message) {
        /*
         * SystemMessage systemMessage = new SystemMessage(
         * systemMessageStr);
         * UserMessage userMessage = new UserMessage(message);
         * Prompt prompt = new Prompt(List.of(systemMessage, userMessage));
         * return chatClient.call(prompt).getResult().getOutput().getContent();
         */

        return chatClient.prompt()
                .system(systemMessageStr)
                .user(message)
                .call()
                .content();
    }

    @GetMapping("/weather")
    public String getCityWeather(@RequestParam(value = "message") String message) {
        /*
         * SystemMessage systemMessage = new SystemMessage(systemMessageStr);
         * UserMessage userMessage = new UserMessage(message);
         * OpenAiChatOptions openAiChatOptions =
         * OpenAiChatOptions.builder().withFunction("currentWeatherFunction")
         * .build();
         * Prompt prompt = new Prompt(List.of(systemMessage, userMessage),
         * openAiChatOptions);
         * return chatClient.call(prompt).getResult().getOutput().getContent();
         */
        return chatClient.prompt()
                .system(systemMessageStr)
                .user(message)
                .functions("currentWeatherFunction")
                .call()
                .content();
    }

}
