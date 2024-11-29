package tms.webapp.taskboard.core.models.migration;

import java.time.LocalDate;
import java.util.UUID;

public class Migration {
    private final int id;
    private final String name;
    private final LocalDate createdAt;
    public Migration(final int id, final String name, final LocalDate createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }
    public int getUuid() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}
