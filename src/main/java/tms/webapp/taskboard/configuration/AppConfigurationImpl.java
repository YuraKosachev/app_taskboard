package tms.webapp.taskboard.configuration;

import jakarta.servlet.ServletContextEvent;
import tms.webapp.taskboard.core.interfaces.configuration.Configuration;
import tms.webapp.taskboard.core.interfaces.store.LocalizationStore;
import tms.webapp.taskboard.core.settings.AppSettings;
import tms.webapp.taskboard.core.store.LocalizationStoreImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfigurationImpl implements Configuration {
    @Override
    public void configure(ServletContextEvent context) throws IOException {
        //set main settings
        AppSettings.configuration(getProperties("app.properties"));

        AppSettings settings = AppSettings.getInstance();

        String translationRealPath = context.getServletContext().getRealPath(settings.getTranslation());
        LocalizationStoreImpl.configurate(translationRealPath);
    }

    private Properties getProperties(String path){
        Properties props = new Properties();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(path)){
            props.load(in);
        }
         catch (IOException e) {
            return null;
        }
        return props;
    }

    public static Configuration getConfiguration() {
        return new AppConfigurationImpl();
    }
}
