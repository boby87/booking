package cm.ftg.bookingHouse.ia.controller;
import cm.ftg.bookingHouse.ia.agent.HouseAiAgent;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class AiAssistantController {
    @Value("${langchain4j.google.api-key}")
    private String apiKey;
    public StreamingChatLanguageModel model =GoogleAiGeminiStreamingChatModel.builder()
                .apiKey("AIzaSyA0yTkaHuD-E1ogiDyfEqgReGX9AdZzAXE")
                .modelName("gemini-1.5-flash")
                .temperature(0.5)
                .build();


    HouseAiAgent houseAiAgent = AiServices.builder(HouseAiAgent.class)
            .streamingChatLanguageModel(model)
            .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
            .build();

    @GetMapping("/ai")
    public Flux<String> chat(@RequestParam String message) {
        return houseAiAgent.chat(message);
    }
}
