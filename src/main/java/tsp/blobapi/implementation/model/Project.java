package tsp.blobapi.implementation.model;

import tsp.blobapi.implementation.response.channel.ReleaseChannelResponse;

/**
 * @author TheSilentPro (Silent)
 */
public record Project(
        String owner,
        String name,
        String description,
        String repo,
        String wiki,
        String[] releaseChannels,
        ReleaseChannelResponse defaultReleaseChannel
) {}