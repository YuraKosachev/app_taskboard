package tms.webapp.taskboard.units.task;

import tms.webapp.taskboard.core.constants.DbConstants;
import tms.webapp.taskboard.core.constants.SqlCommandConstants;
import tms.webapp.taskboard.core.interfaces.db.DbConnector;
import tms.webapp.taskboard.core.interfaces.units.base.DeleteUnit;
import tms.webapp.taskboard.core.interfaces.units.base.InsertUnit;
import tms.webapp.taskboard.core.models.entities.task.TaskCreate;
import tms.webapp.taskboard.units.base.DbExecutorUpdateUnit;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class TaskDeleteUnitImpl extends DbExecutorUpdateUnit<UUID>
        implements DeleteUnit<UUID> {
    public TaskDeleteUnitImpl(DbConnector connector) {
        super(connector);
    }

    @Override
    protected PreparedStatement getPreparedStatement(Connection connection, UUID id) throws SQLException {
        String sql = "DELETE FROM %s WHERE id = ?".formatted(DbConstants.DB_TABLE_TASK);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, String.valueOf(id));
        return preparedStatement;
    }

    @Override
    public int remove(UUID id) {
        return execute(id);
    }
}
