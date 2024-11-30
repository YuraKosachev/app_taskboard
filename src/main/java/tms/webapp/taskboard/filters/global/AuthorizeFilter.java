package tms.webapp.taskboard.filters.global;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tms.webapp.taskboard.core.constants.AppUrlConstants;
import tms.webapp.taskboard.core.constants.CookieConstants;
import tms.webapp.taskboard.core.exceptions.CustomAuthorizationExcetion;
import tms.webapp.taskboard.core.interfaces.services.CryptoService;
import tms.webapp.taskboard.core.models.authorize.UserTicket;
import tms.webapp.taskboard.core.utils.CookieUtils;
import tms.webapp.taskboard.factories.ServiceFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebFilter(value = "/*")
public class AuthorizeFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestUrl = request.getRequestURI();

        if (Arrays.stream(AppUrlConstants.UNAUTHORIZED_URLS).anyMatch(requestUrl::startsWith)) {
            chain.doFilter(request, response);
            return;
        }

        CryptoService cryptoService = ServiceFactory.getCryptoService();
        if (authenticate(request, response, cryptoService)) {
            try {
                chain.doFilter(request, response);
                return;
            } catch (CustomAuthorizationExcetion e) {
                response.sendRedirect(AppUrlConstants.LOGIN_URL);
                return;
            }
        }

        response.sendRedirect(AppUrlConstants.LOGIN_URL);
    }

    protected boolean authenticate(HttpServletRequest req, HttpServletResponse resp, CryptoService cryptoService) {
        Optional<Cookie> authCookieValue = CookieUtils.getCookie(req, CookieConstants.AUTH_COOKIE_NAME);
        UserTicket userPass = authCookieValue.map(x -> cryptoService.getUserTicket(x.getValue())).orElse(null);
        if (Optional.ofNullable(userPass).isPresent()) {
            req.setAttribute("username", userPass.getName());
            req.setAttribute("useremail", userPass.getEmail());
            req.setAttribute("userid", userPass.getId());
            return true;
        }
        return false;
    }
}
