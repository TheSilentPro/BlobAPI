package tsp.blobapi.implementation.response;

import com.google.gson.JsonElement;

import java.util.Optional;

/**
 * @author TheSilentPro (Silent)
 */
public interface Response<T extends JsonElement> {

    boolean isSuccessful();

    String getMessage();

    ResponseCode getResponseCode();

    Optional<T> getData();

}