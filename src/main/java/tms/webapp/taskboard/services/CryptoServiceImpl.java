package tms.webapp.taskboard.services;

import com.google.gson.Gson;
import tms.webapp.taskboard.core.constants.SecurityConstants;
import tms.webapp.taskboard.core.interfaces.services.CryptoService;
import tms.webapp.taskboard.core.models.authorize.UserTicket;
import tms.webapp.taskboard.core.utils.CryptoUtils;

public class CryptoServiceImpl implements CryptoService {
    @Override
    public UserTicket getUserTicket(String code) {
        try {
            String json = CryptoUtils.decrypt(code, SecurityConstants.CRYPTO_KEY);
            return new Gson().fromJson(json, UserTicket.class);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public String encodeUserTicket(UserTicket ticket) {
        String json = new Gson().toJson(ticket);
        return CryptoUtils.encrypt(json, SecurityConstants.CRYPTO_KEY);
    }

    public static CryptoService getInstance() {
        return new CryptoServiceImpl();
    }
}
