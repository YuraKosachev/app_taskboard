package tms.webapp.taskboard.validators;

import tms.webapp.taskboard.core.constants.AppUrlConstants;
import tms.webapp.taskboard.core.interfaces.services.UserService;
import tms.webapp.taskboard.core.interfaces.services.ValidationService;
import tms.webapp.taskboard.core.interfaces.validators.Validator;
import tms.webapp.taskboard.core.models.entities.user.User;
import tms.webapp.taskboard.core.models.entities.user.UserPredicate;
import tms.webapp.taskboard.core.models.validators.ValidationResult;
import tms.webapp.taskboard.factories.ServiceFactory;

import java.util.Optional;

public class CheckUniqueUserValidation implements Validator<String> {
    private final String errorMessage;
    private final UserService userService;

    public CheckUniqueUserValidation(String errorMessage, UserService userService) {
        this.errorMessage = errorMessage;
        this.userService = userService;
    }

    @Override
    public ValidationResult validate(String input) {

        String[] emails = {input};

        Optional<User> optionalUser = Optional.ofNullable(userService.find(new UserPredicate(null, emails, null)));

        return new ValidationResult(optionalUser.isEmpty(), optionalUser.isPresent() ? errorMessage : null);
    }

    public static CheckUniqueUserValidation getInstance(String errorMessage, UserService userService) {
        return new CheckUniqueUserValidation(errorMessage, userService);
    }
}
