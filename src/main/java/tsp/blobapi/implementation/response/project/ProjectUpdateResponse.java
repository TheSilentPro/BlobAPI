package tsp.blobapi.implementation.response.project;

import com.google.gson.JsonObject;
import tsp.blobapi.implementation.model.UpdatedProject;
import tsp.blobapi.implementation.response.BlobResponse;

/**
 * @author TheSilentPro (Silent)
 */
public class ProjectUpdateResponse extends BlobResponse<JsonObject> {

    public ProjectUpdateResponse(JsonObject main) {
        super(main);
    }

    public UpdatedProject getInfo() {
        return GSON.fromJson(getData().orElseThrow().getAsJsonObject(), UpdatedProject.class);
    }

}