package tsp.blobapi;

import com.google.gson.Gson;
import tsp.blobapi.core.BlobClient;
import tsp.blobapi.core.SimpleBlobClient;

import java.net.http.HttpClient;
import java.util.concurrent.ExecutorService;

/**
 * A simple wrapper for <a href="https://blob.build/docs">blob build</a> api.
 *
 * @author TheSilentPro (Silent)
 */
public class BlobAPI {

    /**
     * Gson instance used for {@link tsp.blobapi.implementation.payload.Payload} data deserialization. Feel free to overwrite.
     */
    public static Gson GSON = new Gson();

    public static BlobClient newClient(String token) {
        return new SimpleBlobClient(token);
    }

    public static BlobClient newClient(String token, int threads) {
        return new SimpleBlobClient(token, threads);
    }

    public static BlobClient newClient(String token, ExecutorService executor) {
        return new SimpleBlobClient(token, executor);
    }

    public static BlobClient newClient(String token, ExecutorService executor, HttpClient client) {
        return new SimpleBlobClient(token, executor, client);
    }

}