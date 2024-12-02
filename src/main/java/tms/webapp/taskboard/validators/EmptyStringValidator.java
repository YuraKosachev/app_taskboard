package tms.webapp.taskboard.validators;

import tms.webapp.taskboard.core.interfaces.validators.Validator;
import tms.webapp.taskboard.core.models.validators.ValidationResult;

public class EmptyStringValidator implements Validator<String> {
    private final String errorMessage;
    public EmptyStringValidator(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    @Override
    public  ValidationResult validate(String input) {
        boolean isEmpty = input == null || input.isBlank();
        String message = isEmpty ? errorMessage : null;
        return new ValidationResult(!isEmpty, message);
    }

    public static EmptyStringValidator getInstance(String errorMessage) {
        return new EmptyStringValidator(errorMessage);
    }
}
