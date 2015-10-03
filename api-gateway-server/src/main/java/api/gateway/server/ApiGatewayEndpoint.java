package api.gateway.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity handleRequest(HttpServletRequest request,
                                        HttpServletResponse response,
                                        @PathVariable String apiName,
                                        @PathVariable String apiVersion,
                                        @PathVariable String apiUrl) {

        final EventContext context = new EventContext(apiName, apiVersion, apiUrl, request, response);

        try {
            eventBus.notify("api.request", Event.wrap(context));
        } catch (Exception ex) {
            logger.error("Failed API executing", ex);
            return new ResponseEntity("Fail!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity("OK!", HttpStatus.OK);
    }


}
