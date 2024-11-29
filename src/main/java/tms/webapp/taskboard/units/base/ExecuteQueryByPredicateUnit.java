package tms.webapp.taskboard.units.base;

import tms.webapp.taskboard.core.interfaces.db.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//public abstract class ExecuteQueryByPredicateUnit<T,F> extends DbUnit<F> {
//
//    public ExecuteQueryByPredicateUnit(DbConnector connector) {
//        super(connector);
//    }
//    protected T executeQuery(F filter) {
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
//            try (Connection connection = getConnection()){
//                PreparedStatement statement = getPreparedStatement(connection, filter);
//                return mapper(statement.executeQuery());
//            }
//        }
//        catch(Exception ex){
//            throw new RuntimeException(ex);
//        }
//    }
//
//    protected abstract T mapper(ResultSet rs);
//
//}
