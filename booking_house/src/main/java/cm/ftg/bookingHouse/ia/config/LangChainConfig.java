package cm.ftg.bookingHouse.ia.config;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiStreamingChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class LangChainConfig {

    @Value("${langchain4j.google.api-key}")
    private String apiKey;

   // @Bean
    public ChatLanguageModel chatLanguageModel() {
        return GoogleAiGeminiChatModel.builder()
                .apiKey("AIzaSyA0yTkaHuD-E1ogiDyfEqgReGX9AdZzAXE")
                .modelName("gemini-1.5-flash")
                .temperature(0.0)
                .build();
    }


}
