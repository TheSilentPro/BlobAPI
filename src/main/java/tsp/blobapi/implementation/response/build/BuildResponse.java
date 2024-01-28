package tsp.blobapi.implementation.response.build;

import com.google.gson.JsonObject;
import tsp.blobapi.implementation.model.Build;
import tsp.blobapi.implementation.response.BlobResponse;

/**
 * @author TheSilentPro (Silent)
 */
public class BuildResponse extends BlobResponse<JsonObject> {

    public BuildResponse(JsonObject main) {
        super(main);
    }

    public Build getBuild() {
        return GSON.fromJson(getData().orElseThrow().getAsJsonObject(), Build.class);
    }

}
