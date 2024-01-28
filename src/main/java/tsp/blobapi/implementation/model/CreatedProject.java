package tsp.blobapi.implementation.model;

import tsp.blobapi.implementation.request.ReleaseChannelRequest;
import tsp.blobapi.implementation.response.channel.ReleaseChannelResponse;

/**
 * @author TheSilentPro (Silent)
 */
public record CreatedProject(
        int projectId,
        int userId,
        String name,
        String description,
        String repo,
        String wiki,
        ReleaseChannelResponse defaultReleaseChannel,
        ReleaseChannelRequest... releaseChannels
) {}