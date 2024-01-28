package tsp.blobapi.core.mapper;

import com.google.gson.*;
import tsp.blobapi.implementation.model.Project;
import tsp.blobapi.implementation.response.channel.ReleaseChannelResponse;

import java.lang.reflect.Type;

/**
 * @author TheSilentPro (Silent)
 */
public class ProjectMapper extends JsonMapper<Project> {

    @Override
    public Project deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonObject()) {
            throw new IllegalStateException("Json is not of type: JsonObject");
        }

        JsonObject obj = json.getAsJsonObject();
        JsonObject defChannel = obj.get("defaultReleaseChannel").getAsJsonObject();
        return new Project(
                asString(obj.get("owner")),
                asString(obj.get("name")),
                asString(obj.get("description")),
                asString(obj.get("repoLink")),
                asString(obj.get("wikiLink")),
                toStringArray(obj.get("releaseChannels")),
                new ReleaseChannelResponse(asString(defChannel.get("name")), asString(defChannel.get("supportedVersions")), toStringArray(defChannel.get("dependencies")))
        );
    }

}