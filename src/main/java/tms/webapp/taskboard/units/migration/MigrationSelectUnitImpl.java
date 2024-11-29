package tms.webapp.taskboard.units.migration;

import tms.webapp.taskboard.core.constants.DbConstants;
import tms.webapp.taskboard.core.interfaces.db.DbConnector;
import tms.webapp.taskboard.core.interfaces.units.base.SelectUnit;
import tms.webapp.taskboard.core.models.migration.Migration;
import tms.webapp.taskboard.core.models.migration.MigrationPredicate;
import tms.webapp.taskboard.units.base.DbExecutorQueryUnit;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MigrationSelectUnitImpl
        extends DbExecutorQueryUnit<List<Migration>, MigrationPredicate>
        implements SelectUnit<MigrationPredicate, Migration> {
    public MigrationSelectUnitImpl(DbConnector connector) {
        super(connector);
    }

    @Override
    protected PreparedStatement getPreparedStatement(Connection connection, MigrationPredicate model) throws SQLException {
        String sql = "SELECT id, name, createdAt FROM %s".formatted(DbConstants.DB_TABLE_MIGRATION);

        if (Optional.ofNullable(model).isPresent()) {
            StringBuilder sqlBuilder = new StringBuilder();

            if (model.getNames() != null && model.getNames().length > 0) {
                List<String> names = Arrays.stream(model.getNames()).map("'%s'"::formatted).toList();
                sqlBuilder.append("name IN (%s)".formatted(String.join(",", names)));
            }

            if (model.getIds() != null && model.getIds().length > 0) {
                if (!sqlBuilder.isEmpty()) {
                    sqlBuilder.append(" AND ");
                }
                List<String> ids = Arrays.stream(model.getIds()).mapToObj(String::valueOf).toList();
                sqlBuilder.append("id IN (%s)".formatted(String.join(",", ids)));
            }

            if (!sqlBuilder.isEmpty()) {
                sql = "%s WHERE %s".formatted(sql, sqlBuilder.toString());
            }
        }

        return connection.prepareStatement(sql);
    }

    @Override
    protected List<Migration> mapper(ResultSet rs) {
        try {
            List<Migration> migrations = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                LocalDate date = rs.getDate(3).toLocalDate();
                migrations.add(new Migration(id, name, date));
            }
            return migrations;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<Migration> getAll(MigrationPredicate predicate) {
        return execute(predicate);
    }
}
