package cm.ftg.bookingHouse.ia.config;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentParser;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.googleai.GoogleAiEmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class AiConfig {

    @Bean
    EmbeddingModel embeddingModel(@Value("${langchain4j.google.api-key}") String apiKey) {
        return GoogleAiEmbeddingModel.builder()
                .apiKey(apiKey)
                .modelName("text-embedding-004") // Vérifie le bon modèle d'embedding
                .build();
    }

    @Bean
    EmbeddingStore<TextSegment> embeddingStore(EmbeddingModel embeddingModel){
        return new InMemoryEmbeddingStore<>();
//        return PgVectorEmbeddingStore.builder()
//                .host("localhost")
//                .port(5432)
//                .database("agenticRagDb")
//                .user("admin")
//                .password("1234")
//                .table("data_vs_v3")
//                .dimension(embeddingModel.dimension())
//                .dropTableFirst(true)
//                .build();


    }

    @Bean
    ApplicationRunner loadDocumentToVectorStore(
            ChatLanguageModel chatLanguageModel,
            EmbeddingModel embeddingModel,
            EmbeddingStore<TextSegment> embeddingStore,
            @Value("classpath:/docs/coder proprement.pdf") Resource textResource,
         //   @Value("classpath:/docs") Resource folderResource,
            @Value("classpath:/docs/Software craft.pdf") Resource pdfResource){
        return args -> {

            //List<Document> documents = FileSystemDocumentLoader.loadDocuments(folderResource.getFile().toPath());
            //EmbeddingStoreIngestor.ingest(documents, embeddingStore);
            FileSystemDocumentLoader.loadDocument(textResource.getFile().toPath());

            var ingestor = EmbeddingStoreIngestor.builder()
                    .documentSplitter(DocumentSplitters.recursive(1000,100))
                    .embeddingModel(embeddingModel)
                    .embeddingStore(embeddingStore)
                    .build();
            DocumentParser parser = new ApachePdfBoxDocumentParser();
            Document document = parser.parse(pdfResource.getInputStream());
            ingestor.ingest(document);
          //  loadDataIntoVectorStore(pdfResource, tokenizer, chatLanguageModel,ingestor);
        };


    }


    @Bean
    ContentRetriever contentRetriever(EmbeddingModel embeddingModel, EmbeddingStore<TextSegment> embeddingStore){

        return EmbeddingStoreContentRetriever.builder()
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .maxResults(2)
                .minScore(0.6)
                .build();
    }

}
