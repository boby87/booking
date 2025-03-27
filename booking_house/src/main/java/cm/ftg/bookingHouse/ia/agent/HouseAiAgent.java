package cm.ftg.bookingHouse.ia.agent;

import dev.langchain4j.service.SystemMessage;
import reactor.core.publisher.Flux;

public interface HouseAiAgent {
    @SystemMessage("""
            You are a house agent. You will answer questions using the provided context.""")
    Flux<String> chat(String message);
}
