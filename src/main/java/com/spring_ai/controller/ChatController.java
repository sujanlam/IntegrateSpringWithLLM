package com.spring_ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    //Text Search
    @GetMapping("/chat")
    public String chat(@RequestParam("message") String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

    //Get an image and ask to explain
    @PostMapping("/chat-with-image")
    public String chatWithImage(@RequestPart("message") String message, @RequestPart("image") MultipartFile file) {
        return chatClient.prompt()
                .user(prompt -> prompt
                        .text(message)
                        .media(MimeTypeUtils.IMAGE_PNG, new InputStreamResource(file))
                )
                .call()
                .content();
    }

    //Get an image and ask to explain
    @GetMapping("/chat-with-stream-response")
    public Flux<String> chatwithStream(@RequestParam("message") String message) {
        return chatClient.prompt()
                .user(message)
                .stream()
                .content()
                .doOnNext(s -> {
                    System.out.print(s);
                });
    }

}
