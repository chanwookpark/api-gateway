package api.gateway.server.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

/**
 * @author chanwook
 */
public class ServiceInstanceRegister {

    private final Logger logger = LoggerFactory.getLogger(ServiceInstanceRegister.class);

    private final Environment env;

    private RestTemplate restTemplate = new RestTemplate();

    public ServiceInstanceRegister(Environment env) {
        this.env = env;
    }

    @PostConstruct
    public void register() {
        try {
            final String serverUrl = env.getProperty("service.server.url");

            final String name = env.getProperty("service.instance.name");
            final String url = "url...";
            final String version = env.getProperty("service.instance.version");

            if (StringUtils.hasText(name) && StringUtils.hasText(url) && StringUtils.hasText(version)) {
                ServiceRegisterRequest request = new ServiceRegisterRequest(name, url, version);
                ResponseEntity<String> responseEntity = restTemplate.postForEntity(serverUrl + "/discovery", request, String.class);

                if (!responseEntity.getStatusCode().is2xxSuccessful()) {
                    throw new RuntimeException("Failed service register (response status: " + responseEntity.getStatusCode() + ")");
                }

                if (logger.isInfoEnabled()) {
                    logger.info("Service instance register:: name=" + name + ", version=" + version + ", url=" + url);
                }
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("Service instance info is required!!");
                }
            }
        } catch (Exception ex) {
            logger.error("Service instance register failed", ex);
        }
    }
}
