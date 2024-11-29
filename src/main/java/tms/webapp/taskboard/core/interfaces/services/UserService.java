package tms.webapp.taskboard.core.interfaces.services;

import tms.webapp.taskboard.core.models.entities.user.User;
import tms.webapp.taskboard.core.models.entities.user.UserCreate;
import tms.webapp.taskboard.core.models.entities.user.UserPredicate;

import java.security.NoSuchAlgorithmException;

public interface UserService {
    User find(UserPredicate predicate);
    int add(String name, String email, String password) throws NoSuchAlgorithmException;
}
