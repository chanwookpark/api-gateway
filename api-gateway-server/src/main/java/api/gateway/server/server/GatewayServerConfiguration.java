package api.gateway.server.server;

import api.gateway.server.EventContext;
import api.gateway.server.discovery.ServiceDiscovery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.Environment;
import reactor.bus.Event;
import reactor.bus.EventBus;

import static reactor.bus.selector.Selectors.$;

/**
 * @author chanwook
 */
@Configuration
public class GatewayServerConfiguration {

    @Bean
    public ApiGatewayServer apiGatewayServer(ServiceDiscovery serviceDiscovery) {
        return new ApiGatewayServer(serviceDiscovery);
    }

    @Bean
    public EventBus reactor(ApiGatewayServer server) {
        Environment.initialize();

        Logger logger = LoggerFactory.getLogger("api.gateway.server");

        final EventBus eventBus = EventBus.create(Environment.get());

        eventBus.receive($("api.request"), (Event<EventContext> event) -> {
            logger.info("API Request:: {}", event);
            server.execute(event);
            logger.info("API Response:: {}", event);
            return event;
        });

        return eventBus;
    }
}
