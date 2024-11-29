package tms.webapp.taskboard.core.exceptions;

public class CustomAuthorizationExcetion extends RuntimeException{
    public CustomAuthorizationExcetion( String message) {
        super(message);
    }
}
