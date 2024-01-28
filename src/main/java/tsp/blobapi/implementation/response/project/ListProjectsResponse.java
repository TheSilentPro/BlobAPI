package tsp.blobapi.implementation.response.project;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import tsp.blobapi.implementation.model.Project;
import tsp.blobapi.implementation.response.BlobResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TheSilentPro (Silent)
 */
public class ListProjectsResponse extends BlobResponse<JsonArray> {

    public ListProjectsResponse(JsonObject main) {
        super(main);
    }

    public List<Project> getProjects() {
        List<Project> result = new ArrayList<>();
        JsonArray main = getData().orElseThrow();
        for (JsonElement entry : main) {
            result.add(GSON.fromJson(entry.getAsJsonObject(), Project.class));
        }
        return result;
    }

}