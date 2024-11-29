package tms.webapp.taskboard.units.base;

import tms.webapp.taskboard.core.interfaces.db.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DbExecutorQueryUnit<T,M> extends DbUnit<T,M> {
    public DbExecutorQueryUnit(DbConnector connector) {
        super(connector);
    }

    @Override
    protected T executeQuery(Connection connection, M model) throws SQLException {
        PreparedStatement statement = getPreparedStatement(connection, model);
        return mapper(statement.executeQuery());
    }

    protected abstract T mapper(ResultSet rs);


}
