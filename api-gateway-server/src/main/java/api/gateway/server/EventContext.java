package api.gateway.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chanwook
 */
public class EventContext {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    private final String apiUrl;
    private final String apiVersion;
    private final String apiName;

    public EventContext(String apiName, String apiVersion, String apiUrl, HttpServletRequest request, HttpServletResponse response) {
        this.apiName = apiName;
        this.apiVersion = apiVersion;
        this.apiUrl = apiUrl;
        this.request = request;
        this.response = response;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public String getApiName() {
        return apiName;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    @Override
    public String toString() {
        return "EventContext{" +
                "apiName='" + apiName + '\'' +
                ", request=" + request +
                ", response=" + response +
                ", apiUrl='" + apiUrl + '\'' +
                ", apiVersion='" + apiVersion + '\'' +
                '}';
    }
}
