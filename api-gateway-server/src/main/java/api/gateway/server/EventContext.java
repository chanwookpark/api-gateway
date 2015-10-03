package api.gateway.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chanwook
 */
public class EventContext {

    private final HttpServletRequest httpRequest;
    private final HttpServletResponse httpResponse;

    private final String apiUrl;
    private final String apiVersion;
    private final String apiName;

    private String response;

    public EventContext(String apiName, String apiVersion, String apiUrl, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        this.apiName = apiName;
        this.apiVersion = apiVersion;
        this.apiUrl = apiUrl;
        this.httpRequest = httpRequest;
        this.httpResponse = httpResponse;
    }

    public HttpServletRequest getHttpRequest() {
        return httpRequest;
    }

    public HttpServletResponse getHttpResponse() {
        return httpResponse;
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
                ", httpRequest=" + httpRequest +
                ", httpResponse=" + httpResponse +
                ", apiUrl='" + apiUrl + '\'' +
                ", apiVersion='" + apiVersion + '\'' +
                '}';
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }
}
