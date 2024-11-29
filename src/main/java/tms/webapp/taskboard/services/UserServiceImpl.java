package tms.webapp.taskboard.services;

import tms.webapp.taskboard.core.constants.SecurityConstants;
import tms.webapp.taskboard.core.interfaces.services.UserService;
import tms.webapp.taskboard.core.interfaces.units.base.FindUnit;
import tms.webapp.taskboard.core.interfaces.units.base.InsertUnit;
import tms.webapp.taskboard.core.models.entities.user.User;
import tms.webapp.taskboard.core.models.entities.user.UserCreate;
import tms.webapp.taskboard.core.models.entities.user.UserPredicate;
import tms.webapp.taskboard.core.utils.CryptoUtils;

import java.security.NoSuchAlgorithmException;

public class UserServiceImpl implements UserService {

    private final FindUnit<UserPredicate, User> findUnit;
    private final InsertUnit<UserCreate> insertUnit;

    public UserServiceImpl(FindUnit<UserPredicate, User> findUnit, InsertUnit<UserCreate> insertUnit) {
        this.findUnit = findUnit;
        this.insertUnit = insertUnit;
    }

    @Override
    public User find(UserPredicate predicate) {
        return findUnit.find(predicate);
    }

    @Override
    public int add(String name, String email, String password) throws NoSuchAlgorithmException {
        String hash = CryptoUtils.encryptSHA512String(email + password, SecurityConstants.PASSWORD_HASH_SALT_KEY);
        String doubleHash = CryptoUtils.encryptSHA512String(hash,SecurityConstants.PASSWORD_HASH_SALT_KEY);
        return insertUnit.add(new UserCreate(email, name, doubleHash ));
    }
}
