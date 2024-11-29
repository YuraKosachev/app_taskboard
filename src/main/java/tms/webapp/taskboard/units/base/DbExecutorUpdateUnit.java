package tms.webapp.taskboard.units.base;

import tms.webapp.taskboard.core.interfaces.db.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class DbExecutorUpdateUnit<M> extends DbUnit<Integer,M> {
    public DbExecutorUpdateUnit(DbConnector connector) {
        super(connector);
    }

    //protected
    @Override
    protected Integer executeQuery(Connection connection, M model) throws SQLException {
        PreparedStatement statement = getPreparedStatement(connection, model);
        return statement.executeUpdate();
    }
}
