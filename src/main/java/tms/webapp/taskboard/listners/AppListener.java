package tms.webapp.taskboard.listners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import tms.webapp.taskboard.configuration.AppConfigurationImpl;
import tms.webapp.taskboard.updater.DbUpdater;

import java.util.Properties;

@WebListener
public class AppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("Try to apply migrations");
        try {
            AppConfigurationImpl.getConfiguration().configure(sce);
            DbUpdater.getInstance().applyMigration(sce);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ServletContextListener.super.contextInitialized(sce);
    }
}
