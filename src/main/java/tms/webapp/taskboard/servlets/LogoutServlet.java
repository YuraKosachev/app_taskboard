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
import tms.webapp.taskboard.core.utils.CookieUtils;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = ServletNameConstants.LOGOUT, value = AppUrlConstants.LOGOUT_URL)
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Cookie> cookieOptional = CookieUtils.getCookie(req, CookieConstants.AUTH_COOKIE_NAME);
        if(cookieOptional.isPresent())
        {
            Cookie cookie = cookieOptional.get();
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}