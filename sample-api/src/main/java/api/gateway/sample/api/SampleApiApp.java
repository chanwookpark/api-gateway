package api.gateway.sample.api;

import api.gateway.server.client.ServiceDiscoveryAutoConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author chanwook
 */
@SpringBootApplication
@Import(value = ServiceDiscoveryAutoConfigurer.class)
public class SampleApiApp {
    public static void main(String... args) {
        SpringApplication.run(SampleApiApp.class, args);
    }
}
