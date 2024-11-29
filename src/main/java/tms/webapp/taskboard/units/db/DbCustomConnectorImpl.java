package tms.webapp.taskboard.units.db;

import tms.webapp.taskboard.core.interfaces.db.DbConnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbCustomConnectorImpl implements DbConnector {
        private final String url;
        private final String user;
        private final String password;

    public DbCustomConnectorImpl(String url, String user, String pass) {
            this.url = url;
            this.user = user;
            this.password = pass;
        }

        @Override
        public Connection getConnection() throws SQLException {
            return DriverManager.getConnection(url, user, password);
        }

        public static DbConnector getInstance(String url, String user, String pass ) throws IOException {
            return new DbCustomConnectorImpl(url, user, pass);
        }
}
