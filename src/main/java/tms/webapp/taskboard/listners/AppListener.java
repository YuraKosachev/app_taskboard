package tms.webapp.taskboard.listners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import tms.webapp.taskboard.updater.DbUpdater;
import tms.webapp.taskboard.core.configuration.AppSettings;

@WebListener
public class AppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("Try to apply migrations");
        try {
            DbUpdater.getInstance().applyMigration();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ServletContextListener.super.contextInitialized(sce);
    }
}
