package tms.webapp.taskboard.core.interfaces.services;

import tms.webapp.taskboard.core.interfaces.validators.Validator;
import tms.webapp.taskboard.core.models.validators.ValidatorResult;

public interface ValidationService {
    <T> ValidatorResult validate(T input, Validator<T>... validators);
    ValidatorResult merge(ValidatorResult... results);
}