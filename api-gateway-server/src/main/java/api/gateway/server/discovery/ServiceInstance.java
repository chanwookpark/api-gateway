package api.gateway.server.discovery;

/**
 * @author chanwook
 */
public class ServiceInstance {

    private final String name;
    private final String version;
    private final String url;

    public ServiceInstance(String name, String version, String url) {
        this.name = name;
        this.version = version;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getVersion() {
        return version;
    }

    public boolean hasTarget(String reqName, String reqVersion) {
        return this.name.equals(reqName) && this.version.equals(reqVersion);
    }
}
