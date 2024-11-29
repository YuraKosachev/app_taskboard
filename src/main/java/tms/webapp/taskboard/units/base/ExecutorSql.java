package tms.webapp.taskboard.units.base;

import tms.webapp.taskboard.core.interfaces.db.DbConnector;
import tms.webapp.taskboard.core.interfaces.db.DbExecutor;
import tms.webapp.taskboard.units.db.DbConnectorImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.concurrent.Executor;

public class ExecutorSql implements DbExecutor {

    private final DbConnector dbConnector;

    public ExecutorSql(DbConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    @Override
    public boolean transaction(String... sql) {
        String transactionSql = "START TRANSACTION; %s COMMIT;".formatted(String.join("", sql));
        return executeSql(transactionSql);
    }

    public boolean executeSql(String sql,  DbConnector connector) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = connector.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(sql);
                return statement.execute();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean executeSql(String sql) {
        return executeSql(sql, dbConnector);
    }

    public static DbExecutor getInstance() {
        try {
            return new ExecutorSql(DbConnectorImpl.getInstance());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

//    public static DbExecutor getInstance(DbConnector dbConnector) {
//        try {
//            return new ExecutorSql(dbConnector);
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//    }
}
