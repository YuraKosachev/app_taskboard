package tms.webapp.taskboard.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tms.webapp.taskboard.core.constants.AppUrlConstants;
import tms.webapp.taskboard.core.constants.CookieConstants;
import tms.webapp.taskboard.core.constants.ServletNameConstants;
import tms.webapp.taskboard.core.interfaces.services.AuthorizationService;
import tms.webapp.taskboard.core.interfaces.services.CryptoService;
import tms.webapp.taskboard.core.interfaces.services.UserService;
import tms.webapp.taskboard.core.models.authorize.UserTicket;
import tms.webapp.taskboard.core.models.entities.user.User;
import tms.webapp.taskboard.core.models.entities.user.UserCreate;
import tms.webapp.taskboard.core.models.entities.user.UserPredicate;
import tms.webapp.taskboard.core.utils.CryptoUtils;
import tms.webapp.taskboard.factories.ServiceFactory;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Optional;

@WebServlet(name = ServletNameConstants.REGISTRATION, value =AppUrlConstants.REGISTRATION_URL)
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(AppUrlConstants.REGISTRATION_URL_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        String[] emails = {email};
        UserService userService = ServiceFactory.getUserService();
        Optional<User> optionalUser = Optional.ofNullable(userService.find(new UserPredicate(null, emails, null)));

        if(optionalUser.isPresent()) {
            req.setAttribute("error", "User with same email has already been registered");
            getServletContext().getRequestDispatcher(AppUrlConstants.REGISTRATION_URL_JSP).forward(req, resp);
            return;
        }

        try {
            int result = userService.add(fullname, email, password);
            resp.sendRedirect(AppUrlConstants.LOGIN_URL);;
        } catch (Exception e) {
            req.setAttribute("error", "Something went wrong");
            getServletContext().getRequestDispatcher(AppUrlConstants.REGISTRATION_URL_JSP).forward(req, resp);
        }

    }
}
