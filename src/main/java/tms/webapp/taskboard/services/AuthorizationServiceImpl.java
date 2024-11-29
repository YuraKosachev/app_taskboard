package tms.webapp.taskboard.services;

import tms.webapp.taskboard.core.constants.SecurityConstants;
import tms.webapp.taskboard.core.interfaces.services.AuthorizationService;
import tms.webapp.taskboard.core.interfaces.services.CryptoService;
import tms.webapp.taskboard.core.interfaces.units.base.FindUnit;
import tms.webapp.taskboard.core.models.authorize.AuthorizationResult;
import tms.webapp.taskboard.core.models.authorize.UserTicket;
import tms.webapp.taskboard.core.models.entities.user.User;
import tms.webapp.taskboard.core.models.entities.user.UserPredicate;
import tms.webapp.taskboard.core.utils.CryptoUtils;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.UUID;

public class AuthorizationServiceImpl implements AuthorizationService {

    private final CryptoService cryptoService;
    private final FindUnit<UserPredicate, User> userFindUnit;

    public AuthorizationServiceImpl(CryptoService cryptoService, FindUnit<UserPredicate, User> userFindUnit) {
        this.cryptoService = cryptoService;
        this.userFindUnit = userFindUnit;
    }


    @Override
    public AuthorizationResult getTicket(String login, String password) {
        String[] emails = {login};
        Optional<User> optionalUser = Optional.ofNullable(userFindUnit.find(new UserPredicate(null, emails, null)));

        if (optionalUser.isEmpty()) {
            return new AuthorizationResult(false, null);
        }

        User user = optionalUser.get();
        String hash = CryptoUtils.encryptSHA512String(login + password, SecurityConstants.PASSWORD_HASH_SALT_KEY);
        String inputPasswordHash = CryptoUtils.encryptSHA512String(hash,SecurityConstants.PASSWORD_HASH_SALT_KEY);

        if (!inputPasswordHash.equals(user.getHashPassword())) {
            return new AuthorizationResult(false, null);
        }

        UserTicket ticket = new UserTicket(user.getId(), user.getUsername(), user.getEmail());
        return new AuthorizationResult(true, cryptoService.encodeUserTicket(ticket));
    }

}
