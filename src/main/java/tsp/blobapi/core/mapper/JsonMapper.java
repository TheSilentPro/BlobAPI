package tsp.blobapi.core.mapper;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;

/**
 * @author TheSilentPro (Silent)
 */
public abstract class JsonMapper<T> implements JsonDeserializer<T> {

    protected String asString(JsonElement element) {
        return element == null || element instanceof JsonNull ? null : element.getAsString();
    }

    protected int asInt(JsonElement element) {
        return element == null || element instanceof JsonNull ? -1 : element.getAsInt();
    }

    protected String[] toStringArray(JsonElement element) {
        if (element== null || !element.isJsonArray()) {
            return new String[0];
        }

        JsonArray array = element.getAsJsonArray();
        if (array.isEmpty()) {
            return new String[0];
        }

        String[] result = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            result[i] = array.get(i).getAsString();
        }
        return result;
    }

}