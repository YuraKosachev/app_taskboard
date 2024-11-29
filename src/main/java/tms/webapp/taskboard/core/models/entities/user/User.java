package tms.webapp.taskboard.core.models.entities.user;

import java.time.LocalDate;
import java.util.UUID;

public class User {
    private final UUID id;
    private final String login;
    private final String hash;
    private final String email;
    private final LocalDate createdAt;

    public User(final UUID id, final String username, final String email, final String hash, final LocalDate createdAt) {
        this.id = id;
        this.login = username;
        this.email = email;
        this.hash = hash;
        this.createdAt = createdAt;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public String getHashPassword() {
        return hash;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return login;
    }

    public String getEmail() {
        return email;
    }

}
