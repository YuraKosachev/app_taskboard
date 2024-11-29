package tms.webapp.taskboard.updater;

import tms.webapp.taskboard.core.configuration.AppSettings;
import tms.webapp.taskboard.core.constants.DbConstants;
import tms.webapp.taskboard.core.interfaces.db.DbConnector;
import tms.webapp.taskboard.core.interfaces.db.DbExecutor;
import tms.webapp.taskboard.core.interfaces.services.MigrationService;
import tms.webapp.taskboard.core.models.migration.Migration;
import tms.webapp.taskboard.core.models.migration.MigrationCreate;
import tms.webapp.taskboard.core.models.migration.MigrationPredicate;
import tms.webapp.taskboard.factories.ServiceFactory;
import tms.webapp.taskboard.units.base.ExecutorSql;
import tms.webapp.taskboard.units.db.DbCustomConnectorImpl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DbUpdater {
    private final DbExecutor executor;
    private final MigrationService migrationService;

    public DbUpdater(DbExecutor executor,
                     MigrationService migrationService) {

        this.executor = executor;
        this.migrationService = migrationService;
    }

    public void applyMigration() throws IOException {

        AppSettings settings = AppSettings.getInstance();
        System.out.println("Try to create db if it not exists");
        DbConnector customDbConnector = DbCustomConnectorImpl.getInstance(settings.getDbHost(), settings.getDbUsername(), settings.getDbPassword());
        executor.executeSql("CREATE DATABASE IF NOT EXISTS %s".formatted(settings.getDbName()), customDbConnector);
        executor.executeSql("CREATE TABLE IF NOT EXISTS %s (id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(300) NOT NULL, createdAt DATE NOT NULL);".formatted(DbConstants.DB_TABLE_MIGRATION));

        List<Migration> migrations = migrationService.getAll(null);

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(settings.getDiff()))) {
            for (Path path : stream) {
                String pathName = path.getFileName().toString();
                if (!Files.isDirectory(path)
                        && path.getFileName().toString().endsWith(".sql")
                        && migrations.stream().noneMatch(m -> path.getFileName().toString().equals(m.getName()))) {
                    try (InputStream in = Files.newInputStream(path)) {
                        byte[] bytes = in.readAllBytes();
                        String diffSql = new String(bytes)
                                .replaceAll("\n", " ")
                                .replaceAll("\r", " ")
                                .replaceAll("\\s{2,}", " ");

//                        String migration = "INSERT INTO %s(name, createdAt) VALUES ('%s','%s')"
//                                .formatted(DbConstants.DB_TABLE_MIGRATION, path.getFileName(), LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//                        executor.transaction(diffSql, migration);
                        executor.executeSql(diffSql);
                        migrationService.add(new MigrationCreate(path.getFileName().toString()));
                    }
                }
            }
        }
    }

    public static DbUpdater getInstance() throws IOException {
        return new DbUpdater(ExecutorSql.getInstance(), ServiceFactory.getMigrationService());
    }
}
