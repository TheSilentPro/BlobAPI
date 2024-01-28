package tsp.blobapi.implementation.model;

import tsp.blobapi.implementation.response.channel.ReleaseChannelResponse;

/**
 * @author TheSilentPro (Silent)
 */
public record UpdatedProject(
        int projectId,
        int userId,
        String name,
        String description,
        String repo,
        String wiki,
        ReleaseChannelResponse defaultReleaseChannel
) {}
