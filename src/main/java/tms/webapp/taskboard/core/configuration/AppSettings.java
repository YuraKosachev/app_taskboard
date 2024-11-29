package tms.webapp.taskboard.core.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Properties;

public class AppSettings {
    private final String dbUrlFormat;
    private final String dbUsername;
    private final String dbPassword;
    private final String dbName;
    private final String dbHost;
    private final String diff;

    private static AppSettings instance;

    private AppSettings(String dbUrlFormat,
                        String dbUsername,
                        String dbPassword,
                        String dbName,
                        String dbHost,
                        String diff) {
        this.dbUrlFormat = dbUrlFormat;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
        this.dbName = dbName;
        this.dbHost = dbHost;
        this.diff = diff;
        instance = this;
    }

    public String getDbName() {
        return dbName;
    }

    public String getDbUrlFormat() {
        return dbUrlFormat;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getDbHost() {
        return dbHost;
    }

    public String getDiff() {
        return diff;
    }

    public static AppSettings getInstance() throws IOException {
        if(Optional.ofNullable(instance).isPresent())
            return instance;

        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("D:\\Code\\Java\\Practice\\tsm_taskboard_app\\taskboard\\src\\main\\resources\\app.properties"))) {
            props.load(in);
        }
        String urlFormat = props.getProperty("db_url_format");
        String dbHost = props.getProperty("db_host");
        String dbName = props.getProperty("db_name");
        String dbUser = props.getProperty("db_username");
        String dbPassword = props.getProperty("db_password");
        String diff = props.getProperty("diff_directive");

        return new AppSettings(urlFormat, dbUser, dbPassword, dbName, dbHost, diff);
    }
}
