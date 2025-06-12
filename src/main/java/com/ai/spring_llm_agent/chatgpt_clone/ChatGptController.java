package com.ai.spring_llm_agent.chatgpt_clone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;

@Controller
@CrossOrigin
public class ChatGptController {

    private static final Logger log = LoggerFactory.getLogger(ChatGptController.class);
    private final ChatClient chatClient;

    public ChatGptController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("")
    public String home() {
        return "index";
    }

    @HxRequest
    @PostMapping("/api/chat")
    public HtmxResponse generate(@RequestParam String message, Model model) {
        log.info("User Message: {}", message);
        String response = chatClient.prompt()
                .user(message)
                .call()
                .content();

        model.addAttribute("response", response);
        model.addAttribute("message", message);

        return HtmxResponse.builder()
                .view("response :: responseFragment")
                .view("recent-message-list :: messageFragment")
                .build();
    }

}
