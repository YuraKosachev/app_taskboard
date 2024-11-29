


//public abstract class ExecuteUpdateUnit<T> extends DbUnit<T> {
//
//    public ExecuteUpdateUnit(DbConnector dbConnector) {
//        super(dbConnector);
//    }
//
//    protected int executeUpdate(T model) {
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
//            // команда создания таблицы
//            try (Connection connection = getConnection()){
//                PreparedStatement statement = getPreparedStatement(connection, model);
//                return statement.executeUpdate();
//            }
//        }
//        catch(Exception ex){
//            throw new RuntimeException(ex);
//        }
//    }
//}
