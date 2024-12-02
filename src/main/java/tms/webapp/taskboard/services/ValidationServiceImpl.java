package tms.webapp.taskboard.services;

import tms.webapp.taskboard.core.interfaces.services.ValidationService;
import tms.webapp.taskboard.core.interfaces.validators.Validator;
import tms.webapp.taskboard.core.models.validators.ValidationResult;
import tms.webapp.taskboard.core.models.validators.ValidatorResult;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ValidationServiceImpl implements ValidationService {

    public <T> ValidatorResult validate(T input, Validator<T>... validators) {
        ValidatorResult validatorResult = ValidatorResult.getInstance(true);
        for (Validator validator : validators) {
            ValidationResult result = validator.validate(input);
            validatorResult.setSuccess(result.isValid() && validatorResult.isSuccess());

            if (!result.isValid())
                validatorResult.addError(result.getMessage());
        }
        return validatorResult;
    }

    @Override
    public ValidatorResult merge(ValidatorResult... results) {
        Set<String> errors = new HashSet<>();
        Arrays.stream(results).filter(x->!x.isSuccess()).forEach(x-> errors.addAll(x.getErrors()));
        return new ValidatorResult(errors.isEmpty(), errors);
    }

    public static ValidationService getValidationService() {
        return new ValidationServiceImpl();
    }
}