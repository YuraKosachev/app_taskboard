package tms.webapp.taskboard.units.base;

import tms.webapp.taskboard.core.interfaces.db.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class DbUnit<T,M> {
    private final DbConnector connector;
    public DbUnit(DbConnector connector) {
        this.connector = connector;
    }

    protected Connection getConnection() throws SQLException {
        return connector.getConnection();
    }

    protected T execute(M model) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver")
                    .getDeclaredConstructor().newInstance();
            try (Connection connection = getConnection()){
                return executeQuery(connection, model);
            }
        }
        catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    protected abstract T executeQuery(Connection connection, M model) throws SQLException;
    protected abstract PreparedStatement getPreparedStatement(Connection connection, M model) throws SQLException;
}
