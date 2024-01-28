package tsp.blobapi.core.mapper;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import tsp.blobapi.implementation.model.Build;

import java.lang.reflect.Type;

/**
 * @author TheSilentPro (Silent)
 */
public class BuildMapper extends JsonMapper<Build> {

    @Override
    public Build deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonObject()) {
            throw new IllegalStateException("Json is not of type: JsonObject");
        }

        JsonObject obj = json.getAsJsonObject();
        return new Build(
                asString(obj.get("projectName")),
                asString(obj.get("releaseChannel")),
                asInt(obj.get("buildId")),
                asString(obj.get("checksum")),
                asString(obj.get("fileDownloadUrl")),
                asString(obj.get("supportedVersions")),
                toStringArray(obj.get("dependencies")),
                asString(obj.get("releaseNotes")),
                asString(obj.get("commitHash")),
                asString(obj.get("commitLink"))
        );
    }

}