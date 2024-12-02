package tms.webapp.taskboard.filters.validation;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tms.webapp.taskboard.core.constants.AppUrlConstants;
import tms.webapp.taskboard.core.constants.ServletNameConstants;
import tms.webapp.taskboard.core.enums.HttpMethod;
import tms.webapp.taskboard.core.interfaces.services.UserService;
import tms.webapp.taskboard.core.interfaces.services.ValidationService;
import tms.webapp.taskboard.core.models.validators.ValidatorResult;
import tms.webapp.taskboard.core.utils.JsonConverter;
import tms.webapp.taskboard.factories.ServiceFactory;
import tms.webapp.taskboard.services.ValidationServiceImpl;
import tms.webapp.taskboard.validators.CheckUniqueUserValidation;
import tms.webapp.taskboard.validators.EmptyStringValidator;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@WebFilter(servletNames = {ServletNameConstants.REGISTRATION})
public class RegistrationValidationFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        if(!req.getMethod().equals(HttpMethod.POST.name()))
        {
            chain.doFilter(req, res);
            return;
        }
        String username = req.getParameter("username");
        String email = req.getParameter("email");

        ValidationService validationService = ServiceFactory.getValidationService();
        UserService userService = ServiceFactory.getUserService();

        ValidatorResult nameResult = validationService
                .validate(username, new EmptyStringValidator("username field should not be empty"));

        ValidatorResult emailResult = validationService
                .validate(email, new EmptyStringValidator("email field should not be empty"),
                        new CheckUniqueUserValidation("User with same email has already been registered", userService)
                );

        ValidatorResult result = validationService.merge(nameResult, emailResult);
        if(!result.isSuccess())
        {
            String message =String.join(";",result.getErrors());
            req.setAttribute("error", String.join(";",result.getErrors()));
            getServletContext().getRequestDispatcher(AppUrlConstants.REGISTRATION_URL_JSP).forward(req, res);
            return;
        }
        chain.doFilter(req, res);
    }
//
//    private ValidatorResult merge(ValidatorResult... results){
//        Set<String> errors = new HashSet<>();
//        Arrays.stream(results).filter(x->!x.isSuccess()).forEach(x-> errors.addAll(x.getErrors()));
//        return new ValidatorResult(errors.isEmpty(), errors);
//    }
}