package com.ai.spring_llm_agent.functions;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final ChatClient chatClient;
    private static final String systemMessageStr = "You are a helpful AI assistant answering questions about cities across the world";

    public CityController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    // @GetMapping()
    // public String getCityInfo(@RequestParam(value = "message") String message) {
    // SystemMessage systemMessage = new SystemMessage(
    // systemMessageStr);
    // UserMessage userMessage = new UserMessage(message);
    // Prompt prompt = new Prompt(List.of(systemMessage, userMessage));
    // ChatResponse chatResponse = chatClient.call(prompt);
    // return chatResponse.getResult().getOutput().getContent();
    // }

    @GetMapping("/weather")
    public String getCityWeather(@RequestParam(value = "message") String message) {
        SystemMessage systemMessage = new SystemMessage(systemMessageStr);
        UserMessage userMessage = new UserMessage(message);
        OpenAiChatOptions openAiChatOptions = OpenAiChatOptions.builder().withFunction("currentWeatherFunction")
                .build();
        Prompt prompt = new Prompt(List.of(systemMessage, userMessage), openAiChatOptions);
        ChatResponse response = chatClient.call(prompt);
        return response.getResult().getOutput().getContent();
    }

}
