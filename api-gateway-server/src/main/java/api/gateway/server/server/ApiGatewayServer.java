package api.gateway.server.server;

import api.gateway.server.EventContext;
import api.gateway.server.discovery.ServiceDiscovery;
import api.gateway.server.discovery.ServiceInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import reactor.bus.Event;

/**
 * @author chanwook
 */
public class ApiGatewayServer {
    private final Logger logger = LoggerFactory.getLogger("api.gateway.server");

    RestTemplate restTemplate = new RestTemplate();

    ServiceDiscovery serviceDiscovery;

    public void execute(Event<EventContext> event) {
        logger.info("Pass through Gateway::: " + event);
        final EventContext context = event.getData();

        ServiceInstance instance =
                serviceDiscovery.getInstance(context.getApiName(), context.getApiVersion());

        context.setResponse("OK!");
    }
}
