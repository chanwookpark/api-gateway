package api.gateway.server.discovery;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chanwook
 */
@Configuration
public class ServiceDiscoveryConfiguration {

    @Bean
    public ServiceDiscovery serviceDiscovery() {
        return new ServiceDiscoveryMap();
    }
}
