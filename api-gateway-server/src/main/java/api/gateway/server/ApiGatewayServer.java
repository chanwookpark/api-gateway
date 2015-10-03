package api.gateway.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.bus.Event;

/**
 * @author chanwook
 */
public class ApiGatewayServer {
    private final Logger logger = LoggerFactory.getLogger("api.gateway.server");

    public void execute(Event<EventContext> event) {
        logger.info("Pass through Gateway::: " + event);
    }
}
