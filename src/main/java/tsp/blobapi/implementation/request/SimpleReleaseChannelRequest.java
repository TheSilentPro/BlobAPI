package tsp.blobapi.implementation.request;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

/**
 * @author TheSilentPro (Silent)
 */
public record SimpleReleaseChannelRequest(@Nonnull String name, @Nonnull String supportedVersions, @Nullable String fileNaming, @Nullable String... dependencies) implements ReleaseChannelRequest {

    public SimpleReleaseChannelRequest(@Nonnull String name, @Nonnull String supportedVersions, @Nullable String... dependencies) {
        this(name, supportedVersions, null, dependencies);
    }

    public SimpleReleaseChannelRequest(@Nonnull String name, @Nonnull String supportedVersions) {
        this(name, supportedVersions, null, (String[]) null);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSupportedVersions() {
        return supportedVersions;
    }

    @Override
    public Optional<String> getFileNaming() {
        return Optional.ofNullable(fileNaming);
    }

    @Override
    public Optional<String[]> getDependencies() {
        return Optional.ofNullable(dependencies);
    }

}