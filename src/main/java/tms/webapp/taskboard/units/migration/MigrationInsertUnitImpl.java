package tms.webapp.taskboard.units.migration;

import tms.webapp.taskboard.core.constants.DbConstants;
import tms.webapp.taskboard.core.interfaces.db.DbConnector;
import tms.webapp.taskboard.core.interfaces.units.base.InsertUnit;
import tms.webapp.taskboard.core.models.migration.MigrationCreate;
import tms.webapp.taskboard.units.base.DbExecutorUpdateUnit;

import java.sql.*;
import java.time.LocalDate;

public class MigrationInsertUnitImpl
        extends DbExecutorUpdateUnit<MigrationCreate>
        implements InsertUnit<MigrationCreate> {

    public MigrationInsertUnitImpl(DbConnector dbConnector) {
        super(dbConnector);
    }

    @Override
    public int add(MigrationCreate model) {
        return execute(model);
    }

    @Override
    protected PreparedStatement getPreparedStatement(Connection connection, MigrationCreate model) throws SQLException {
        String sql = "INSERT INTO %s(name, createdAt) VALUES (?,?)".formatted(DbConstants.DB_TABLE_MIGRATION);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, model.getName());
        preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
        return preparedStatement;
    }
}
