package tms.webapp.taskboard.services;

import tms.webapp.taskboard.core.interfaces.services.MigrationService;
import tms.webapp.taskboard.core.interfaces.units.base.InsertUnit;
import tms.webapp.taskboard.core.interfaces.units.base.SelectUnit;
import tms.webapp.taskboard.core.models.migration.Migration;
import tms.webapp.taskboard.core.models.migration.MigrationCreate;
import tms.webapp.taskboard.core.models.migration.MigrationPredicate;

import java.util.List;

public class MigrationServiceImpl implements MigrationService {
    private final SelectUnit<MigrationPredicate, Migration> migrationSelectUnit;
    private final InsertUnit<MigrationCreate> migrationInsertUnit;
    public MigrationServiceImpl(SelectUnit<MigrationPredicate, Migration> migrationSelectUnit,
                                InsertUnit<MigrationCreate> migrationInsertUnit) {

        this.migrationSelectUnit = migrationSelectUnit;
        this.migrationInsertUnit = migrationInsertUnit;
    }

    @Override
    public List<Migration> getAll(MigrationPredicate predicate) {
        return migrationSelectUnit.getAll(predicate);
    }

    @Override
    public int add(MigrationCreate migration) {
        return migrationInsertUnit.add(migration);
    }
}
