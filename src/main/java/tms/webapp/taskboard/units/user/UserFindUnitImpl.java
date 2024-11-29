package tms.webapp.taskboard.units.user;

import tms.webapp.taskboard.core.constants.DbConstants;
import tms.webapp.taskboard.core.constants.SqlCommandConstants;
import tms.webapp.taskboard.core.interfaces.db.DbConnector;
import tms.webapp.taskboard.core.interfaces.units.base.FindUnit;
import tms.webapp.taskboard.core.models.entities.user.User;
import tms.webapp.taskboard.core.models.entities.user.UserPredicate;

import tms.webapp.taskboard.units.base.DbExecutorQueryUnit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class UserFindUnitImpl
        extends DbExecutorQueryUnit<User, UserPredicate>
        implements FindUnit<UserPredicate, User> {
    public UserFindUnitImpl(DbConnector connector) {
        super(connector);
    }

    @Override
    protected User mapper(ResultSet rs) {
        try {
            if (!rs.next()) {
                return null;
            }
            UUID id = UUID.fromString(rs.getString(1));
            String name = rs.getString(2);
            String hash = rs.getString(3);
            String email = rs.getString(4);
            LocalDate created = rs.getDate(5).toLocalDate();
            return new User(id, name, email, hash, created);

        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    protected PreparedStatement getPreparedStatement(Connection connection, UserPredicate model) throws SQLException {
        String sql = "SELECT id, name, hash, email, createdAt FROM %s"
                .formatted(DbConstants.DB_TABLE_USER);

        if (Optional.ofNullable(model).isPresent()) {
            StringBuilder sqlBuilder = new StringBuilder();

            if (model.getNames() != null && model.getNames().length > 0) {
                List<String> names = Arrays.stream(model.getNames()).map("'%s'"::formatted).toList();
                sqlBuilder.append("name IN (%s)".formatted(String.join(",", names)));
            }

            if (model.getEmails() != null && model.getEmails().length > 0) {
                if (!sqlBuilder.isEmpty()) {
                    sqlBuilder.append(" AND ");
                }
                List<String> emails = Arrays.stream(model.getEmails()).map("'%s'"::formatted).toList();
                sqlBuilder.append("email IN (%s)".formatted(String.join(",", emails)));
            }

            if (!sqlBuilder.isEmpty()) {
                sql = "%s WHERE %s".formatted(sql, sqlBuilder.toString());
            }
        }

        return connection.prepareStatement("%s LIMIT 1".formatted(sql));
    }

    @Override
    public User find(UserPredicate predicate) {
        return execute(predicate);
    }
}
