package tms.webapp.taskboard.core.models.authorize;

public class AuthorizationResult {
    private final boolean success;
    private final String token;
    public AuthorizationResult(final boolean success, final String token) {
        this.success = success;
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    public boolean isSuccess() {
        return success;
    }
}
