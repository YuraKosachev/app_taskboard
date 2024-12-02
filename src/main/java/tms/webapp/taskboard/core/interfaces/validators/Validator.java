package tms.webapp.taskboard.core.interfaces.validators;

import tms.webapp.taskboard.core.models.validators.ValidationResult;

public interface Validator<T> {
    ValidationResult validate(T input);
}
