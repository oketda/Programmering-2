package TestClient;

import no.ntnu.idatx2001.oblig5.zoo.ScandinavianWildAnimal;
import no.ntnu.idatx2001.oblig5.zoo.WildAnimalFactory;
import no.ntnu.idatx2001.oblig5.zoo.ZooLogger;

import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class testClient {

    //private static final Logger LOGGER = Logger.getLogger(ZooLogger.class.getName());

    public static void main(String[] args) throws IOException {
        WildAnimalFactory wildAnimalFactory = WildAnimalFactory.getInstance();
        ZooLogger zooLogger = new ZooLogger();

        ScandinavianWildAnimal ulla = wildAnimalFactory.newFemaleWolf("Ulla", LocalDate.of(2006, 3, 13), LocalDate.of(2008, 1, 10), "Innhegning 2", 4);
        System.out.println(ulla.printInfo());

        //Test that fails and logs exception to logFile.log
        try {
            ScandinavianWildAnimal logTest = wildAnimalFactory.newFemaleWolf("", LocalDate.of(2006, 3, 13), LocalDate.of(2008, 1, 10), "Innhegning 2", 4);
        }catch (Exception e){
            zooLogger.LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }
}
