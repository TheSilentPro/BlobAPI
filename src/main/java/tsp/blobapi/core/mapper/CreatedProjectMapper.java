package tsp.blobapi.core.mapper;

import com.google.gson.*;
import tsp.blobapi.implementation.model.CreatedProject;
import tsp.blobapi.implementation.request.ReleaseChannelRequest;
import tsp.blobapi.implementation.request.SimpleReleaseChannelRequest;
import tsp.blobapi.implementation.response.channel.ReleaseChannelResponse;

import java.lang.reflect.Type;

/**
 * @author TheSilentPro (Silent)
 */
public class CreatedProjectMapper extends JsonMapper<CreatedProject> {

    @Override
    public CreatedProject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject main = json.getAsJsonObject();
        JsonObject project = main.get("project").getAsJsonObject();

        return new CreatedProject(
                asInt(project.get("projectId")),
                asInt(project.get("userId")),
                asString(project.get("name")),
                asString(project.get("description")),
                asString(project.get("repoLink")),
                asString(project.get("wikiLink")),
                new ReleaseChannelResponse(asString(project.get("name")), asString(project.get("supportedVersions")), toStringArray(project.get("dependencies"))),
                parse(main.get("releaseChannels").getAsJsonArray())
        );
    }

    private ReleaseChannelRequest[] parse(JsonArray array) {
        if (array == null || array.isEmpty()) {
            return new SimpleReleaseChannelRequest[0];
        }

        ReleaseChannelRequest[] result = new ReleaseChannelRequest[array.size()];
        for (int i = 0; i < array.size(); i++) {
            JsonObject obj = array.get(i).getAsJsonObject(); // projectId is ignored as it is already known from the "project" json data.
            result[i] = new SimpleReleaseChannelRequest(asString(obj.get("name")), asString(obj.get("supportedVersions")), asString(obj.get("fileNaming")), toStringArray(obj.get("dependencies")));
        }
        return result;
    }

}
