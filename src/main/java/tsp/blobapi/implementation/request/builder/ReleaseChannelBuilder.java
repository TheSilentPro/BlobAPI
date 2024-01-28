package tsp.blobapi.implementation.request.builder;

import tsp.blobapi.implementation.request.ReleaseChannelRequest;

/**
 * @author TheSilentPro (Silent)
 */
public interface ReleaseChannelBuilder {

    static ReleaseChannelBuilder newBuilder() {
        return new SimpleReleaseChannelBuilder();
    }

    ReleaseChannelBuilder name(String name);

    ReleaseChannelBuilder supportedVersions(String versions);

    default ReleaseChannelBuilder versions(String versions) {
        return supportedVersions(versions);
    }

    ReleaseChannelBuilder fileNaming(String fileNaming);

    ReleaseChannelBuilder dependencies(String... dependencies);

    ReleaseChannelRequest build();

}
