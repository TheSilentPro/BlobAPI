package tsp.blobapi.implementation.payload;

import com.google.gson.JsonElement;
import tsp.blobapi.BlobAPI;

/**
 * @author TheSilentPro (Silent)
 */
public interface Payload<T extends JsonElement> {

    T getData();

    default String getJson() {
        return BlobAPI.GSON.toJson(getData());
    }

}