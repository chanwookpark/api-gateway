package api.gateway.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.bus.Event;
import reactor.bus.EventBus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chanwook
 */
@RestController
public class ApiGatewayEndpoint {

    private final Logger logger = LoggerFactory.getLogger("api.gateway.endpoint");

    @Autowired
    EventBus eventBus;

    @RequestMapping(value = "/info/{apiName}/{apiVersion}/{apiUrl}")
    public String info(@PathVariable String apiName, @PathVariable String apiVersion,
                       @PathVariable String apiUrl) {

        return "API INFO: " + apiName + ";" + apiVersion + ";" + apiUrl;
    }

    @RequestMapping(value = "/{apiName}/{apiVersion}/{apiUrl}")
    public void handleRequest(HttpServletRequest request,
                              HttpServletResponse response,
                              @PathVariable String apiName,
                              @PathVariable String apiVersion,
                              @PathVariable String apiUrl) {

        final EventContext context = new EventContext(apiName, apiVersion, apiUrl, request, response);

        try {
            final Event<EventContext> event = Event.wrap(context, "api.response");

            eventBus.sendAndReceive("api.request", event, (Event<EventContext> s) -> {
                System.out.print("HTTP Endpoint Result::: " + s.getData());
            });
        } catch (Exception ex) {
            logger.error("Failed API executing", ex);
            throw new RuntimeException("Fail!");
        }
    }
}
