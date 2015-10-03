package api.gateway.server.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author chanwook
 */
@Configuration
@EnableConfigurationProperties
public class ServiceDiscoveryAutoConfigurer {

    @Bean
    public ServiceInstanceRegister serviceInstanceRegister(Environment env) {
        return new ServiceInstanceRegister(env);
    }

    @ConfigurationProperties(prefix = "service.instance")
    public static class ServiceDiscoveryProperties {

        private String name;

        private String url;

        private String version;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
