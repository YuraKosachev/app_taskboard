package tms.webapp.taskboard.units.base;
//
//import tms.webapp.taskboard.core.interfaces.db.DbConnector;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
////Use for SELECT statments
//public abstract class ExecuteQueryUnit<T> {
//
//    private final DbConnector dbConnector;
//    public ExecuteQueryUnit(DbConnector connector) {
//        this.dbConnector = connector;
//    }
//    protected T executeQuery() {
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
//            try (Connection connection = dbConnector.getConnection()){
//                PreparedStatement statement = getPreparedStatement(connection);
//                return mapper(statement.executeQuery());
//            }
//        }
//        catch(Exception ex){
//            throw new RuntimeException(ex);
//        }
//    }
//
//    protected abstract PreparedStatement getPreparedStatement(Connection connection) throws SQLException;
//    protected abstract T mapper(ResultSet rs);
//
//}
