package tsp.blobapi.implementation.model;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

/**
 * @author TheSilentPro (Silent)
 */
@ParametersAreNonnullByDefault
public record Build(
        String projectName,
        String releaseChannel,
        int buildId,
        String checksum,
        String fileDownloadUrl,
        String supportedVersions,
        String[] dependencies,
        @Nullable String releaseNotes,
        @Nullable String commitHash,
        @Nullable String commitLink
) {

    public Optional<String> getReleaseNotes() {
        return Optional.ofNullable(releaseNotes);
    }

    public Optional<String> getCommitHash() {
        return Optional.ofNullable(commitHash);
    }

    public Optional<String> getCommitLink() {
        return Optional.ofNullable(commitLink);
    }

}