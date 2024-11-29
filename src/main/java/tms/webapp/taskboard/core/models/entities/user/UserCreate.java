package tms.webapp.taskboard.core.models.entities.user;

public class UserCreate {
    private final String email;
    private final String name;
    private final String hash;

    public UserCreate(final String email, final String name, final String hash) {
        this.email = email;
        this.name = name;
        this.hash = hash;
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public String getPasswordHash() {
        return hash;
    }

}
