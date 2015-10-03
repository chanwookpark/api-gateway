package api.gateway.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.spring.context.config.EnableReactor;

/**
 * @author chanwook
 */
@SpringBootApplication
@EnableReactor
public class ApiGatewayServerApp {

    public static void main(String... args) {
        SpringApplication.run(ApiGatewayServerApp.class, args);
    }
}
