package multichoice.util;

import java.io.File;
import org.apache.log4j.PropertyConfigurator;
/**
 * 
 * @author Noopur Berge
 */
public class LoggerUtil 
{
    public static void setupLogging()
    {
        final String configurationFile = System.getProperty("user.dir") + File.separator + "lib" + File.separator + "log4j.properties";
        System.setProperty("log4j.configuration", configurationFile);
        PropertyConfigurator.configure(configurationFile);
    }
}
