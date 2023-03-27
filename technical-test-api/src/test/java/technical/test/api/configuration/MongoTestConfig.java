package technical.test.api.configuration;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.Network;

@Configuration
public class MongoTestConfig {

    private static final String MONGO_IMAGE = "mongo:latest";

    @Bean(destroyMethod = "stop")
    public MongoDBContainer mongoContainer() {
        MongoDBContainer container = new MongoDBContainer(MONGO_IMAGE)
                .withReuse(true);
        container.start();
        return container;
    }

    @Bean
    public MongoClient mongoClient(GenericContainer<?> mongoContainer) {
        String host = mongoContainer.getHost();
        int port = mongoContainer.getFirstMappedPort();
        return MongoClients.create("mongodb://" + host + ":" + port);
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate(MongoClient mongoClient) {
        return new ReactiveMongoTemplate(mongoClient, "test");
    }

}
