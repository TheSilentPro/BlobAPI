package tsp.blobapi.implementation.response.project;

import com.google.gson.JsonObject;
import tsp.blobapi.implementation.model.Project;
import tsp.blobapi.implementation.response.BlobResponse;

/**
 * @author TheSilentPro (Silent)
 */
public class ProjectResponse extends BlobResponse<JsonObject> {

    public ProjectResponse(JsonObject main) {
        super(main);
    }

    public Project getProject() {
        return GSON.fromJson(getData().orElseThrow().getAsJsonObject(), Project.class);
    }

}