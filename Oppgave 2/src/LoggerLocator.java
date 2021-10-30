import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerLocator {

    public static final Logger logger = Logger.getLogger(LoggerLocator.class.getName());
    public static final FileHandler fh;

    static {
        try {
            logger.setUseParentHandlers(false);
            fh = new FileHandler("log.txt", true);
            fh.setFormatter(new SimpleFormatter());
            fh.setLevel(Level.FINE);
            logger.addHandler(fh);
            logger.setLevel(Level.FINE);
        } catch (IOException ioe) {
            throw new ExceptionInInitializerError(ioe);
        }
    }

    public LoggerLocator() throws IOException {
    }
}
