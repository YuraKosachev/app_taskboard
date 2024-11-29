package tms.webapp.taskboard.core.models.response;

public class BaseResponse {
    private final boolean success;
    private final String message;
    public BaseResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    public boolean isSuccess() {
        return success;
    }
    public String getMessage() {
        return message;
    }

    public static BaseResponse getInstance(boolean success, String message) {
        return new BaseResponse(success, message);
    }
}
