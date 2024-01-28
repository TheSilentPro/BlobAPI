package tsp.blobapi.core;

import tsp.blobapi.implementation.request.ReleaseChannelRequest;
import tsp.blobapi.implementation.response.build.BuildResponse;
import tsp.blobapi.implementation.response.build.ListBuildsResponse;
import tsp.blobapi.implementation.response.project.ListProjectsResponse;
import tsp.blobapi.implementation.response.project.ProjectResponse;
import tsp.blobapi.implementation.response.project.ProjectCreateResponse;
import tsp.blobapi.implementation.response.project.ProjectUpdateResponse;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

/**
 * @author TheSilentPro (Silent)
 */
public interface BlobRequester {

    // GET

    CompletableFuture<ListProjectsResponse> getProjects();

    CompletableFuture<ProjectResponse> getProject(String name);

    CompletableFuture<ListBuildsResponse> getProjectBuilds(String name);

    CompletableFuture<BuildResponse> getLatestBuild(String name, String channel);

    CompletableFuture<BuildResponse> getBuild(String name, String channel, int buildId);

    // PATCH

    CompletableFuture<ProjectUpdateResponse> updateProject(String name, String newName, String newDescription, @Nullable String newRepo);

    default CompletableFuture<ProjectUpdateResponse> updateProject(String name, String newName, String newDescription) {
        return updateProject(name, newName, newDescription, null);
    }

    // POST

    CompletableFuture<ProjectCreateResponse> createProject(String name, String description, @Nullable String repo, @Nullable ReleaseChannelRequest... channels);

    default CompletableFuture<ProjectCreateResponse> createProject(String name, String description, @Nullable String repo) {
        return createProject(name, description, repo, (ReleaseChannelRequest[]) null);
    }

    default CompletableFuture<ProjectCreateResponse> createProject(String name, String description) {
        return createProject(name, description, null);
    }

}