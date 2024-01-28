package tsp.blobapi.core;

import java.net.http.HttpClient;
import java.util.concurrent.ExecutorService;

/**
 * @author TheSilentPro (Silent)
 */
public interface BlobClient extends BlobRequester {

    ExecutorService getExecutor();

    HttpClient getClient();

    String getToken();

}