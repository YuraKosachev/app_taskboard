package tms.webapp.taskboard.core.interfaces.services;

import tms.webapp.taskboard.core.models.migration.Migration;
import tms.webapp.taskboard.core.models.migration.MigrationCreate;
import tms.webapp.taskboard.core.models.migration.MigrationPredicate;

import java.util.List;

public interface MigrationService {
    List<Migration> getAll(MigrationPredicate predicate);
    int add(MigrationCreate migration);
}
