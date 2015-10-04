package api.gateway.server.endpoint;

import api.gateway.server.EventContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import reactor.bus.Event;
import reactor.bus.EventBus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public ResponseBodyEmitter handleRequest(HttpServletRequest request,
                                             HttpServletResponse response,
                                             @PathVariable String apiName,
                                             @PathVariable String apiVersion,
                                             @PathVariable String apiUrl) {

        final EventContext requestContext = new EventContext(apiName, apiVersion, apiUrl, request, response);
        final ResponseBodyEmitter emitter = new ResponseBodyEmitter();

        try {
            final Event<EventContext> event = Event.wrap(requestContext);

            this.eventBus.sendAndReceive("api.request", event,
                    (Event<EventContext> s) -> {
                        try {
                            final EventContext responseContext = s.getData();
                            emitter.send(responseContext.getResponse(), MediaType.APPLICATION_JSON);
                            emitter.complete();
                        } catch (IOException e) {
                            logger.error("Failed...", e);
                            emitter.completeWithError(e);
                        }
                    });

        } catch (Exception ex) {
            logger.error("Failed API executing", ex);
            emitter.completeWithError(ex);
        }
        return emitter;
    }
}
