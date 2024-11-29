package tms.webapp.taskboard.units.db;

import tms.webapp.taskboard.core.configuration.AppSettings;
import tms.webapp.taskboard.core.constants.DbConstants;
import tms.webapp.taskboard.core.interfaces.db.DbConnector;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

public class DbConnectorImpl implements DbConnector {
    private static DbConnector instance;
    private final String url;
    private final String user;
    private final String password;

    private DbConnectorImpl(String url, String user, String pass) {
        this.url = url;
        this.user = user;
        this.password = pass;
        instance = this;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static DbConnector getInstance() throws IOException {

        if(Optional.ofNullable(instance).isPresent())
            return instance;

        AppSettings settings = AppSettings.getInstance();
        String host = settings.getDbUrlFormat().formatted(settings.getDbName());

        return new DbConnectorImpl(host, settings.getDbUsername(), settings.getDbPassword());
    }
}

