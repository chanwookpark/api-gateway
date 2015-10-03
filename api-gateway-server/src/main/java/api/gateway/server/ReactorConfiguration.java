package api.gateway.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.Environment;
import reactor.bus.Event;
import reactor.bus.EventBus;
import reactor.bus.selector.Selectors;

/**
 * @author chanwook
 */
@Configuration
public class ReactorConfiguration {

    @Bean
    public ApiGatewayServer apiGatewayServer() {
        return new ApiGatewayServer();
    }

    @Bean
    public EventBus reactor(ApiGatewayServer server, Environment env) {

        Logger logger = LoggerFactory.getLogger("api.gateway.server");

        final EventBus eventBus = EventBus.create(env);

        eventBus.on(Selectors.$("api.request"), (Event<EventContext> event) -> {
            logger.info("API Request: {}", event);
            server.execute(event);
        });

        return eventBus;
    }
}
