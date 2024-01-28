package tsp.blobapi.implementation.response.channel;

/**
 * @author TheSilentPro (Silent)
 */
public record ReleaseChannelResponse(String name, String supportedVersions, String[] dependencies) {}