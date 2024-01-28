package tsp.blobapi.implementation.payload;

import com.google.gson.JsonElement;

/**
 * @author TheSilentPro (Silent)
 */
public class SimplePayload<T extends JsonElement> implements Payload<T> {

    private final T data;

    public SimplePayload(T data) {
        this.data = data;
    }

    @Override
    public T getData() {
        return data;
    }

}