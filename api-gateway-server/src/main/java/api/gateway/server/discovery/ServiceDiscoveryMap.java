package api.gateway.server.discovery;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chanwook
 */
public class ServiceDiscoveryMap implements ServiceDiscovery {

    Map<String, ServiceInstance> map = new ConcurrentHashMap<>();

    @Override
    public ServiceInstance getInstance(String name, String version) {
        for (ServiceInstance instance : map.values()) {
            if (instance.hasTarget(name, version)) {
                return instance;
            }
        }
        throw new RuntimeException("Not found service instance");
    }

    @Override
    public void addInstance(String apiName, String version, String url) {
        final ServiceInstance instance = new ServiceInstance(apiName, version, url);
        map.put(UUID.randomUUID().toString(), instance);
    }
}
