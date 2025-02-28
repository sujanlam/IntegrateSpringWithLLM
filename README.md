Create Configuration class and create a Bean of ChatClient

@Configuration
public class ChatClientConfiguration {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder){
        return builder.build();
    }
}
=======================================================================================================
@PostMapping("/chat-with-image")
    public String chat(@RequestPart ("message") String message, @RequestPart("image") MultipartFile file){
        return chatClient.prompt()
                .user(prompt -> prompt
                    .text(message)
                    .media(MimeTypeUtils.IMAGE_PNG,new InputStreamResource(file))
                )
                .call()
                .content();
    }
=======================================================================================================
On Postman, under form-data:
 1) Set message as: what you want to for the image
 2) create a new key image and upload an image

URL: http://localhost:8080/chat-with-image
