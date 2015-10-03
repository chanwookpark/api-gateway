package api.gateway.server.client;

/**
 * @author chanwook
 */
public class ServiceRegisterRequest {

    private String version;
    private String url;
    private String name;

    public ServiceRegisterRequest() {
    }

    public ServiceRegisterRequest(String name, String url, String version) {
        this.name = name;
        this.url = url;
        this.version = version;
    }

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
