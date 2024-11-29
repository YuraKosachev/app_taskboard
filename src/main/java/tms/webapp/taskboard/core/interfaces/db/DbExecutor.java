package tms.webapp.taskboard.core.interfaces.db;

public interface DbExecutor {
    boolean executeSql(String sql);
    boolean executeSql(String sql, DbConnector connector);
    boolean transaction(String... sql);
}
