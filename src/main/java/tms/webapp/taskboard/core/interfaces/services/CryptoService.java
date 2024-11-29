package tms.webapp.taskboard.core.interfaces.services;

import tms.webapp.taskboard.core.models.authorize.UserTicket;

public interface CryptoService {
    UserTicket getUserTicket(String code);
    String encodeUserTicket(UserTicket ticket);
}
