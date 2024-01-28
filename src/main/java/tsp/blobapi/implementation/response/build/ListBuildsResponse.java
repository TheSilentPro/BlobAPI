package tsp.blobapi.implementation.response.build;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import tsp.blobapi.implementation.model.Build;
import tsp.blobapi.implementation.response.BlobResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author TheSilentPro (Silent)
 */
public class ListBuildsResponse extends BlobResponse<JsonObject> {

    public ListBuildsResponse(JsonObject main) {
        super(main);
    }

    public Map<String, List<Build>> getBuilds() {
        Map<String, List<Build>> result = new HashMap<>();
        JsonObject main = getData().orElseThrow();
        for (Map.Entry<String, JsonElement> entry : main.entrySet()) { // Loop channel entries
            List<Build> entryBuilds = new ArrayList<>();
            JsonArray buildsArray = entry.getValue().getAsJsonArray(); // Array of build objcets
            for (JsonElement buildEntry : buildsArray) { // Loop array of build objects
                entryBuilds.add(GSON.fromJson(buildEntry, Build.class)); // Add to temp list
            }
            result.put(entry.getKey(), entryBuilds); // Add to result
        }
        return result;
    }

}