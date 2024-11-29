package tms.webapp.taskboard.units.user;

import tms.webapp.taskboard.core.constants.DbConstants;
import tms.webapp.taskboard.core.constants.SqlCommandConstants;
import tms.webapp.taskboard.core.interfaces.db.DbConnector;
import tms.webapp.taskboard.core.interfaces.units.base.InsertUnit;
import tms.webapp.taskboard.core.models.entities.user.UserCreate;
import tms.webapp.taskboard.units.base.DbExecutorUpdateUnit;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserInsertUnitImpl extends DbExecutorUpdateUnit<UserCreate>
        implements InsertUnit<UserCreate> {
    public UserInsertUnitImpl(DbConnector connector) {
        super(connector);
    }

    @Override
    public int add(UserCreate model) {
        return execute(model);
    }

    @Override
    protected PreparedStatement getPreparedStatement(Connection connection, UserCreate model) throws SQLException {
        String sql = "INSERT INTO %s(id, name,email,createdAt,hash) VALUES(%s,?,?,?,?)"
                .formatted(DbConstants.DB_TABLE_USER, SqlCommandConstants.GENERATE_UUID_ID_FUNC);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, model.getName());
        preparedStatement.setString(2, model.getEmail());
        preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));
        preparedStatement.setString(4, model.getPasswordHash());
        return preparedStatement;
    }
}
