package constants;

public final class AssertionErrorMessages {

    private AssertionErrorMessages() {
    }

    public static final String STATUS_CODE_IS_NOT_SUCCESSFUL = "Status is not 200, but - %s";

    public static final String STATUS_CODE_IS_NOT_ERRORED = "Status is not 500, but - %s";

    public static final String WRONG_RESPONSE_CONTENT_TYPE = "Response content type is not %s, but - %s";

    public static final String TO_LONG_RESPONSE_TIME = "X-VIA-PURGE-KEY";

    public static final String WRONG_PET_WAS_CREATED = "Wrong pet was created";

}
