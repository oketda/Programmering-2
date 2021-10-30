import javafx.stage.Stage;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.*;

public class JunitTestBonusMember {

    @Test
    public void testInvalidParametersInConstructor() {
        try {
            BonusMember bm = new BasicMember(12, null, null); // Should throw exception
            fail(); // If I get to this line, the test has failed
        } catch (IllegalArgumentException e) {
            LoggerLocator.logger.log(Level.SEVERE, e.toString(), e);
        }
    }

    @Test
    public void testInvalidPassword(){
        try{
            BonusMember bm = new BasicMember(100, new Personals("s", "s", "s", ""), LocalDate.now());
            bm.okPassword(bm.getPersonals().getPassword());
            fail(); // If I get to this line, the test has failed
        } catch (IllegalArgumentException e){
            LoggerLocator.logger.log(Level.SEVERE, e.toString(), e);
        }
    }


    public void fail(){
        System.out.println("Testen feilet");
    }

    public static void main(String[] args) {
        JunitTestBonusMember obj = new JunitTestBonusMember();

        obj.testInvalidParametersInConstructor();
        obj.testInvalidPassword();
    }
}
