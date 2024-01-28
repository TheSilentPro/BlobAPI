package tsp.blobapi.implementation.response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import tsp.blobapi.core.mapper.BuildMapper;
import tsp.blobapi.core.mapper.CreatedProjectMapper;
import tsp.blobapi.core.mapper.ProjectMapper;
import tsp.blobapi.core.mapper.UpdatedProjectMapper;
import tsp.blobapi.implementation.model.Build;
import tsp.blobapi.implementation.model.CreatedProject;
import tsp.blobapi.implementation.model.Project;
import tsp.blobapi.implementation.model.UpdatedProject;

import java.util.Optional;

/**
 * @author TheSilentPro (Silent)
 */
public class BlobResponse<T extends JsonElement> implements Response<T> {

    protected final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Project.class, new ProjectMapper())
            .registerTypeAdapter(Build.class, new BuildMapper())
            .registerTypeAdapter(CreatedProject.class, new CreatedProjectMapper())
            .registerTypeAdapter(UpdatedProject.class, new UpdatedProjectMapper())
            .create();

    private final boolean success;
    private final String message;
    private final ResponseCode code;
    private final T data;

    public BlobResponse(boolean success, String message, ResponseCode code, T data) {
        this.success = success;
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public BlobResponse(JsonObject main) {
        this.success = main.get("success").getAsBoolean();
        JsonElement rawCode = main.get("code");
        if (rawCode == null || rawCode.isJsonNull()) {
            // No error code
            this.code = ResponseCode.OK;
            this.message = main.get("message").getAsString();
            try {
                //noinspection unchecked
                this.data = (T) main.get("data");
            } catch (ClassCastException ex) {
                throw new IllegalStateException("Data object can not be cast to type!");
            }
        } else {
            // Error
            this.code = ResponseCode.match(main.get("code").getAsInt()).orElse(null);
            this.message = main.get("error").getAsString();
            this.data = null;
        }
    }

    @Override
    public boolean isSuccessful() {
        return success;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public ResponseCode getResponseCode() {
        return code;
    }

    @Override
    public Optional<T> getData() {
        return Optional.ofNullable(data);
    }

    public boolean hasData() {
        return getData().isPresent();
    }

}