package tms.webapp.taskboard.core.models.migration;

import java.time.LocalDate;

public class MigrationCreate {
    private final String name;

    public MigrationCreate(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
