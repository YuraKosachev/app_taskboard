package tms.webapp.taskboard.units.task;

import tms.webapp.taskboard.core.constants.DbConstants;
import tms.webapp.taskboard.core.constants.SqlCommandConstants;
import tms.webapp.taskboard.core.interfaces.db.DbConnector;
import tms.webapp.taskboard.core.interfaces.units.base.InsertUnit;
import tms.webapp.taskboard.core.interfaces.units.base.UpdateUnit;
import tms.webapp.taskboard.core.models.entities.task.Task;
import tms.webapp.taskboard.core.models.entities.task.TaskCreate;
import tms.webapp.taskboard.units.base.DbExecutorUpdateUnit;

import java.sql.*;
import java.time.LocalDate;

public class TaskInsertUnitImpl extends DbExecutorUpdateUnit<TaskCreate>
        implements InsertUnit<TaskCreate> {
    public TaskInsertUnitImpl(DbConnector connector) {
        super(connector);
    }

    @Override
    protected PreparedStatement getPreparedStatement(Connection connection, TaskCreate model) throws SQLException {
        String sql = "INSERT INTO %s (id,title,description,descriptionHtml,priority,status,userId,createdAt) ".formatted(DbConstants.DB_TABLE_TASK) +
                "VALUES (%s,?,?,?,?,?,?,?);".formatted(SqlCommandConstants.GENERATE_UUID_ID_FUNC);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, model.getTitle());
        if (model.getDescription() != null)
            preparedStatement.setString(2, model.getDescription());
        else
            preparedStatement.setNull(2, Types.NVARCHAR);

        if (model.getDescriptionHtml() != null)
            preparedStatement.setString(3, model.getDescriptionHtml());
        else
            preparedStatement.setNull(3, Types.NVARCHAR);

        if(model.getPriority() != null)
            preparedStatement.setInt(4, model.getPriority().getValue());
        else
            preparedStatement.setNull(4, Types.INTEGER);

        if(model.getStatus() != null)
            preparedStatement.setInt(5, model.getStatus().getValue());
        else
            preparedStatement.setNull(5, Types.INTEGER);

        preparedStatement.setString(6, String.valueOf(model.getUserId()));
        preparedStatement.setDate(7, Date.valueOf(LocalDate.now()));

        return preparedStatement;
    }

    @Override
    public int add(TaskCreate model) {
        return execute(model);
    }
}