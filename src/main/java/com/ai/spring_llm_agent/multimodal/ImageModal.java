package com.ai.spring_llm_agent.multimodal;

import java.io.IOException;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageModal {

    private final ChatClient chatClient;

    public ImageModal(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Value("classpath:/images/sincerely-media-2UlZpdNzn2w-unsplash.jpg")
    private Resource sampleImage;

    @Value("classpath:/images/java-open-ai.png")
    private Resource sampleCodeImage;

    @GetMapping("/image-describe")
    public String describeImage() throws IOException {
        /*
         * byte[] imageData = new
         * ClassPathResource("/images/sincerely-media-2UlZpdNzn2w-unsplash.jpg")
         * .getContentAsByteArray();
         * UserMessage userMessage = new
         * UserMessage("Can you please explain what you see in the following image?",
         * List.of(new Media(MimeTypeUtils.IMAGE_JPEG, imageData)));
         * Prompt prompt = new Prompt(userMessage);
         * return chatClient.call(prompt).getResult().getOutput().getContent();
         */

        return chatClient.prompt()
                .user(u -> u.text(
                        "Can you please explain what you see in the following image?")
                        .media(MimeTypeUtils.IMAGE_JPEG, sampleImage))
                .call()
                .content();
    }

    @GetMapping("/code-describe")
    public String describeCode() throws IOException {
        /*
         * byte[] imageData = new
         * ClassPathResource("images/java-open-ai.png").getContentAsByteArray();
         * UserMessage userMessage = new UserMessage(
         * "The following is the screenshot of some code. Can you do your best to describe the what this code does?"
         * ,
         * List.of(new Media(MimeTypeUtils.IMAGE_PNG, imageData)));
         * Prompt prompt = new Prompt(userMessage);
         * return chatClient.call(prompt).getResult().getOutput().getContent();
         */

        return chatClient.prompt()
                .user(u -> u.text(
                        "The following is the screenshot of some code. Can you do your best to describe the what this code does?")
                        .media(MimeTypeUtils.IMAGE_PNG, sampleCodeImage))
                .call()
                .content();
    }

    @GetMapping("/image-to-code")
    public String imageToCode() throws IOException {
        /*
         * byte[] imageData = new
         * ClassPathResource("images/java-open-ai.png").getContentAsByteArray();
         * UserMessage userMessage = new UserMessage(
         * "The following is the screenshot of some code. Can you do your best to translate this code into a Java class?"
         * ,
         * List.of(new Media(MimeTypeUtils.IMAGE_PNG, imageData)));
         * Prompt prompt = new Prompt(userMessage);
         * return chatClient.call(prompt).getResult().getOutput().getContent();
         */

        return chatClient.prompt()
                .user(u -> u.text(
                        "The following is the screenshot of some code. Can you do your best to translate this code into a Java class?")
                        .media(MimeTypeUtils.IMAGE_PNG, sampleCodeImage))
                .call()
                .content();
    }

}
