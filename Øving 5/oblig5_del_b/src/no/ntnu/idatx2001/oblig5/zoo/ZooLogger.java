package no.ntnu.idatx2001.oblig5.zoo;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ZooLogger {

   public static final Logger LOGGER = Logger.getLogger(ZooLogger.class.getName());
    private static FileHandler fh;

    public ZooLogger() throws IOException {
        fh = new FileHandler("logFile.log");
        LOGGER.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
        LOGGER.setUseParentHandlers(false);
    }
}
