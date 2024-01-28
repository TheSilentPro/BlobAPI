package tsp.blobapi.implementation.response.project;

import com.google.gson.JsonObject;
import tsp.blobapi.implementation.model.CreatedProject;
import tsp.blobapi.implementation.response.BlobResponse;

/**
 * @author TheSilentPro (Silent)
 */
public class ProjectCreateResponse extends BlobResponse<JsonObject> {

    public ProjectCreateResponse(JsonObject main) {
        super(main);
    }

    public CreatedProject getInfo() {
        return GSON.fromJson(getData().orElseThrow().getAsJsonObject(), CreatedProject.class);
    }

}