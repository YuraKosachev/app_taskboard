package tms.webapp.taskboard.core.interfaces.configuration;

import jakarta.servlet.ServletContextEvent;

import java.io.IOException;

public interface Configuration {
    void configure(ServletContextEvent context) throws IOException;
}
