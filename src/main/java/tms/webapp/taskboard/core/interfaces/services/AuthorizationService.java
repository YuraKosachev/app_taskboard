package tms.webapp.taskboard.core.interfaces.services;
import tms.webapp.taskboard.core.models.authorize.AuthorizationResult;

import java.security.NoSuchAlgorithmException;

public interface AuthorizationService {
    AuthorizationResult getTicket(String login, String password);
}
