package tsp.blobapi.core.mapper;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import tsp.blobapi.implementation.model.UpdatedProject;
import tsp.blobapi.implementation.response.channel.ReleaseChannelResponse;

import java.lang.reflect.Type;

/**
 * @author TheSilentPro (Silent)
 */
public class UpdatedProjectMapper extends JsonMapper<UpdatedProject> {
    
    @Override
    public UpdatedProject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject main = json.getAsJsonObject();

        return new UpdatedProject(
                asInt(main.get("projectId")),
                asInt(main.get("userId")),
                asString(main.get("name")),
                asString(main.get("description")),
                asString(main.get("repoLink")),
                asString(main.get("wikiLink")),
                new ReleaseChannelResponse(asString(main.get("name")), asString(main.get("supportedVersions")), toStringArray(main.get("dependencies")))
        );
    }
    
}
