package tsp.blobapi.implementation.request;

import tsp.blobapi.implementation.request.builder.ReleaseChannelBuilder;
import tsp.blobapi.implementation.request.builder.SimpleReleaseChannelBuilder;

import java.util.Optional;

/**
 * @author TheSilentPro (Silent)
 */
public interface ReleaseChannelRequest {

    static ReleaseChannelBuilder newBuilder() {
        return new SimpleReleaseChannelBuilder();
    }

    String getName();

    String getSupportedVersions();

    default Optional<String> getFileNaming() {
        return Optional.empty();
    }

    default Optional<String[]> getDependencies() {
        return Optional.empty();
    }

}