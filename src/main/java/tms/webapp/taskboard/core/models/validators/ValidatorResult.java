package tms.webapp.taskboard.core.models.validators;

import java.util.HashSet;
import java.util.Set;

public class ValidatorResult {
    private boolean success;
    private Set<String> errors;
    public ValidatorResult(boolean success) {
        this.success = success;
        this.errors = new HashSet<>();
    }
    public ValidatorResult(boolean success, Set<String> errors) {
        this.success = success;
        this.errors = errors;
    }
    public void addError(String error) {
        this.errors.add(error);
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
    public Set<String> getErrors() {
        return errors;
    }

    public static ValidatorResult getInstance(boolean success)
    {
        return new ValidatorResult(success);
    }
    public static ValidatorResult getInstance(boolean success, HashSet<String> errors)
    {
        return new ValidatorResult(success, errors);
    }
}
