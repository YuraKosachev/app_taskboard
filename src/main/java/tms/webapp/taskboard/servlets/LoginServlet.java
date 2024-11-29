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
import tms.webapp.taskboard.core.models.authorize.AuthorizationResult;
import tms.webapp.taskboard.factories.ServiceFactory;

import java.io.IOException;

@WebServlet(name= ServletNameConstants.LOGIN, value = AppUrlConstants.LOGIN_URL)
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(AppUrlConstants.LOGIN_URL_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("login");
        String password = req.getParameter("password");

        AuthorizationService authorizationService = ServiceFactory.getAuthorizationService();

        AuthorizationResult result = authorizationService.getTicket(username, password);

        if (result.isSuccess()) {
            String code = result.getToken();
            Cookie cookie = new Cookie(CookieConstants.AUTH_COOKIE_NAME, code);
            cookie.setMaxAge(CookieConstants.AUTH_MAX_AGE);
            cookie.setHttpOnly(true);
            resp.addCookie(cookie);
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        req.setAttribute("error", "User not found. Check the entered data");
        getServletContext().getRequestDispatcher(AppUrlConstants.LOGIN_URL_JSP).forward(req, resp);
    }
}
