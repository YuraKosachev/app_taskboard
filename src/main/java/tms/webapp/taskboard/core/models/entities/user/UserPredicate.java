package tms.webapp.taskboard.core.models.entities.user;

import java.util.UUID;

public class UserPredicate {
    private final UUID[] uuids;
    private final String[] emails;
    private final String[] names;

    public UserPredicate(final UUID[] uuids, final String[] emails, final String[] names) {
        this.uuids = uuids;
        this.emails = emails;
        this.names = names;
    }
    public UUID[] getUuids() {
        return uuids;
    }
    public String[] getEmails() {
        return emails;
    }
    public String[] getNames() {
        return names;
    }
}
