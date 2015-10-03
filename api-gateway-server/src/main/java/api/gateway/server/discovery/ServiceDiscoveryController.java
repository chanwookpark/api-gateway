package api.gateway.server.discovery;

import api.gateway.server.client.ServiceRegisterRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chanwook
 */
@RestController
public class ServiceDiscoveryController {

    private final Logger logger = LoggerFactory.getLogger(ServiceDiscoveryController.class);

    @Autowired
    ServiceDiscovery discovery;

    @RequestMapping(value = "/discovery", method = RequestMethod.POST)
    public String addInstance(@RequestBody ServiceRegisterRequest request) {

        final String name = request.getName();
        final String version = request.getVersion();
        final String url = request.getUrl();

        discovery.addInstance(name, version, url);

        if (logger.isInfoEnabled()) {
            logger.info("Add Service instance::name=" + name + ", version=" + version + ", url=" + url);
        }

        return "OK!";
    }
}
