package tsp.blobapi.implementation.request.builder;

import tsp.blobapi.implementation.request.ReleaseChannelRequest;
import tsp.blobapi.implementation.request.SimpleReleaseChannelRequest;

/**
 * @author TheSilentPro (Silent)
 */
public class SimpleReleaseChannelBuilder implements ReleaseChannelBuilder {

    private String name;
    private String versions;
    private String fileNaming;
    private String[] dependencies;

    @Override
    public ReleaseChannelBuilder name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public ReleaseChannelBuilder supportedVersions(String versions) {
        this.versions = versions;
        return this;
    }

    @Override
    public ReleaseChannelBuilder fileNaming(String fileNaming) {
        this.fileNaming = fileNaming;
        return this;
    }

    @Override
    public ReleaseChannelBuilder dependencies(String... dependencies) {
        this.dependencies = dependencies;
        return this;
    }

    @Override
    public ReleaseChannelRequest build() {
        return new SimpleReleaseChannelRequest(name, versions, fileNaming, dependencies);
    }

}