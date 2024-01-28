package tsp.blobapi.core;

import com.google.gson.*;
import tsp.blobapi.implementation.endpoint.Endpoint;
import tsp.blobapi.implementation.payload.SimplePayload;
import tsp.blobapi.implementation.request.ReleaseChannelRequest;
import tsp.blobapi.implementation.response.build.BuildResponse;
import tsp.blobapi.implementation.response.build.ListBuildsResponse;
import tsp.blobapi.implementation.response.project.ListProjectsResponse;
import tsp.blobapi.implementation.response.project.ProjectResponse;
import tsp.blobapi.implementation.response.project.ProjectCreateResponse;
import tsp.blobapi.implementation.response.project.ProjectUpdateResponse;

import javax.annotation.Nullable;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author TheSilentPro (Silent)
 */
public class SimpleBlobClient implements BlobClient {

    private final ExecutorService executor;
    private final HttpClient client;
    private final String token;

    public SimpleBlobClient(String token, ExecutorService executor, HttpClient client) {
        this.executor = executor;
        this.client = client;
        this.token = token;
    }

    public SimpleBlobClient(String token, ExecutorService executor) {
        this(token, executor, HttpClient.newBuilder().executor(executor).build());
    }

    public SimpleBlobClient(String token, int threads) {
        this(token, Executors.newFixedThreadPool(threads));
    }

    public SimpleBlobClient(String token) {
        this(token, Executors.newSingleThreadExecutor());
    }

    @Override
    public ExecutorService getExecutor() {
        return executor;
    }

    @Override
    public HttpClient getClient() {
        return client;
    }

    @Override
    public String getToken() {
        return token;
    }

    // GET

    @Override
    public CompletableFuture<ListProjectsResponse> getProjects() {
        return getClient().sendAsync(Endpoint.LIST_PROJECTS.get((Object[]) null), HttpResponse.BodyHandlers.ofString()).thenApplyAsync(json -> new ListProjectsResponse(JsonParser.parseString(json.body()).getAsJsonObject()));
    }

    @Override
    public CompletableFuture<ProjectResponse> getProject(String name) {
        return getClient().sendAsync(Endpoint.GET_PROJECT.get(name), HttpResponse.BodyHandlers.ofString()).thenApplyAsync(json -> new ProjectResponse(JsonParser.parseString(json.body()).getAsJsonObject()));
    }

    @Override
    public CompletableFuture<ListBuildsResponse> getProjectBuilds(String name) {
        return getClient().sendAsync(Endpoint.LIST_BUILDS.get(name), HttpResponse.BodyHandlers.ofString()).thenApplyAsync(json -> new ListBuildsResponse(JsonParser.parseString(json.body()).getAsJsonObject()));
    }

    @Override
    public CompletableFuture<BuildResponse> getLatestBuild(String name, String channel) {
        return getClient().sendAsync(Endpoint.LATEST_BUILD.get(name, channel), HttpResponse.BodyHandlers.ofString()).thenApplyAsync(json -> new BuildResponse(JsonParser.parseString(json.body()).getAsJsonObject()));
    }

    @Override
    public CompletableFuture<BuildResponse> getBuild(String name, String channel, int buildId) {
        return getClient().sendAsync(Endpoint.SPECIFIC_BUILD.get(name, channel, buildId), HttpResponse.BodyHandlers.ofString()).thenApplyAsync(json -> new BuildResponse(JsonParser.parseString(json.body()).getAsJsonObject()));
    }

    // PATCH

    @Override
    public CompletableFuture<ProjectUpdateResponse> updateProject(String name, String newName, String newDescription, @Nullable String newRepo) {
        JsonObject payload = new JsonObject();
        payload.addProperty("name", newName);
        payload.addProperty("description", newDescription);
        if (newRepo != null) {
            payload.addProperty("repoLink", newRepo);
        }
        return getClient().sendAsync(Endpoint.UPDATE_PROJECT.patch(new SimplePayload<>(payload), getToken(), name), HttpResponse.BodyHandlers.ofString()).thenApplyAsync(json -> new ProjectUpdateResponse(JsonParser.parseString(json.body()).getAsJsonObject()));
    }

    // POST

    @Override
    public CompletableFuture<ProjectCreateResponse> createProject(String name, String description, @Nullable String repo, @Nullable ReleaseChannelRequest... channels) {
        JsonObject payload = new JsonObject();
        payload.addProperty("name", name);
        payload.addProperty("description", description);

        if (repo != null) {
            payload.addProperty("repoLink", repo);
        }

        // Add channels
        if (channels != null) {
            JsonArray jsonChannels = new JsonArray();
            for (ReleaseChannelRequest channel : channels) {
                JsonObject obj = new JsonObject();

                obj.addProperty("name", channel.getName());
                obj.addProperty("supportedVersions", channel.getSupportedVersions());
                channel.getFileNaming().ifPresent(format -> obj.addProperty("fileNaming", format));
                channel.getDependencies().ifPresent(dependencies -> {
                    JsonArray jsonDependencies = new JsonArray();
                    for (String dependency : dependencies) {
                        jsonDependencies.add(dependency);
                    }
                    obj.add("dependencies", jsonDependencies);
                });

                jsonChannels.add(obj);
            }
            payload.add("releaseChannels", jsonChannels);
        }

        return getClient().sendAsync(Endpoint.CREATE_PROJECT.post(new SimplePayload<>(payload), getToken(), name), HttpResponse.BodyHandlers.ofString()).thenApplyAsync(json -> new ProjectCreateResponse(JsonParser.parseString(json.body()).getAsJsonObject()));
    }

}