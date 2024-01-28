package tsp.blobapi.implementation.endpoint;

import tsp.blobapi.implementation.payload.Payload;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpRequest;

/**
 * @author TheSilentPro (Silent)
 */
public enum Endpoint {

    BASE("https://blob.build/api"),

    // Project
    LIST_PROJECTS("/projects"),
    GET_PROJECT("/projects/%s"), // project name
    UPDATE_PROJECT("/projects/%s"), // project name
    CREATE_PROJECT("/projects/%s/new"), // project name

    // Build
    LIST_BUILDS("/builds/%s"), // project name
    LATEST_BUILD("/builds/%s/%s/latest"), // project name, release channel
    SPECIFIC_BUILD("/builds/%s/%s/%d"), // project name, release channel, build id

    // Upload
    ;

    private final String url;
    private HttpRequest request;

    Endpoint(String url) {
        this.url = url;
        this.request = null;
    }

    public String getCompleteUrl(Object... data) {
        return String.format(BASE.getUrl() + url, data);
    }

    public String getCompleteUrl() {
        return BASE.getUrl() + url;
    }

    public String getUrl() {
        return url;
    }

    public HttpRequest get(Object... data) {
        if (request == null) {
            try {
                this.request = HttpRequest.newBuilder()
                        .uri(new URL(data != null ? getCompleteUrl(data) : getCompleteUrl()).toURI())
                        .header("User-Agent", "BlobAPI")
                        .header("Content-Type", "application/json")
                        .GET()
                        .build();
            } catch (URISyntaxException | MalformedURLException ex) {
                throw new RuntimeException(ex);
            }
        }

        return request;
    }

    public HttpRequest patch(Payload<?> payload, String token, Object... data) {
        if (request == null) {
            try {
                this.request = HttpRequest.newBuilder()
                        .uri(new URL(data != null ? getCompleteUrl(data) : getCompleteUrl()).toURI())
                        .header("User-Agent", "BlobAPI")
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + token)
                        .method("PATCH", HttpRequest.BodyPublishers.ofString(payload.getJson()))
                        .build();
            } catch (URISyntaxException | MalformedURLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return request;
    }

    public HttpRequest post(Payload<?> payload, String token, Object... data) {
        if (request == null) {
            try {
                this.request = HttpRequest.newBuilder()
                        .uri(new URL(data != null ? getCompleteUrl(data) : getCompleteUrl()).toURI())
                        .header("User-Agent", "BlobAPI")
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + token)
                        .POST(HttpRequest.BodyPublishers.ofString(payload.getJson()))
                        .build();
            } catch (URISyntaxException | MalformedURLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return request;
    }

}