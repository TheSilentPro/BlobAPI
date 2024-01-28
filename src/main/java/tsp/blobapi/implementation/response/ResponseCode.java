package tsp.blobapi.implementation.response;


import java.util.Optional;

/**
 * Response codes from <a href="https://blob.build/docs/errors/">blob build page</a>.
 *
 * @author TheSilentPro (Silent)
 */
public enum ResponseCode {

    OK(200),
    // Blob codes
    INTERNAL_ERROR(0),
    ROUTE_NOT_FOUND(1),
    ADMIN_ONLY(2),
    MISSING_FIELDS(1000),
    INVALID_JSON(1001),
    ERROR_UPLOAD(1004),
    MISSING_UPLOAD(1005),
    INVALID_AUTHORIZATION(2000),
    INVALID_TOKEN(2001),
    PROJECT_NOT_FOUND(4000),
    PROJECT_ALREADY_EXISTS(4001),
    INVALID_CHANNEL(5000),
    BUILD_NOT_FOUND(6000),
    INVALID_BUILD_ID(6001);

    public static final ResponseCode[] VALUES = values();
    private final int code;

    ResponseCode(int code) {
        this.code = code;
    }

    public static Optional<ResponseCode> match(int code) {
        for (ResponseCode value : VALUES) {
            if (value.code == code) {
                return Optional.of(value);
            }
        }

        return Optional.empty();
    }

    public int getCode() {
        return code;
    }

}