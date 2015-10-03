package api.gateway.server.discovery;

/**
 * @author chanwook
 */
public interface ServiceDiscovery {

    ServiceInstance getInstance(String apiName, String apiVersion);

    void addInstance(String apiName, String version, String url);
}
