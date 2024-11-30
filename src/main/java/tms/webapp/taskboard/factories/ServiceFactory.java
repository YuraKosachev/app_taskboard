package tms.webapp.taskboard.factories;

import tms.webapp.taskboard.core.interfaces.services.*;
import tms.webapp.taskboard.services.*;
import tms.webapp.taskboard.units.db.DbConnectorImpl;
import tms.webapp.taskboard.units.migration.MigrationInsertUnitImpl;
import tms.webapp.taskboard.units.migration.MigrationSelectUnitImpl;
import tms.webapp.taskboard.units.task.*;
import tms.webapp.taskboard.units.user.UserFindUnitImpl;
import tms.webapp.taskboard.units.user.UserInsertUnitImpl;

import java.io.IOException;

public final class ServiceFactory {
    public static AuthorizationService getAuthorizationService() throws IOException {
        return new AuthorizationServiceImpl(new CryptoServiceImpl(), new UserFindUnitImpl(DbConnectorImpl.getInstance()));
    }

    public static CryptoService getCryptoService(){
        return CryptoServiceImpl.getInstance();
    }

    public static MigrationService getMigrationService() throws IOException {
        return new MigrationServiceImpl(new MigrationSelectUnitImpl(DbConnectorImpl.getInstance()),
                new MigrationInsertUnitImpl(DbConnectorImpl.getInstance()));
    }

    public static UserService getUserService() throws IOException {
        return new UserServiceImpl(new UserFindUnitImpl(DbConnectorImpl.getInstance()),
                new UserInsertUnitImpl(DbConnectorImpl.getInstance()));
    }

    public static TaskService getTaskService() throws IOException {
        return new TaskServiceImpl(new TaskSelectUnitImpl(DbConnectorImpl.getInstance()),
                new TaskUpdateUnitImpl(DbConnectorImpl.getInstance()),
                new TaskDeleteUnitImpl(DbConnectorImpl.getInstance()),
                new TaskInsertUnitImpl(DbConnectorImpl.getInstance()),
                new TaskSetPriorityUpdateUnitImpl(DbConnectorImpl.getInstance()),
                new TaskSetStatusUpdateUnitImpl(DbConnectorImpl.getInstance()),
                new TaskPagedSelectUnitImpl(DbConnectorImpl.getInstance())
        );
    }

    public static LanguageService getLanguageService() throws IOException {
        return new LanguageServiceImpl();
    }
}
