package dk.mhr.pioneer;
/**
 * Created by mortenrummelhoff on 18/02/16.
 */

import dk.mhr.pioneer.telnet.CommunicationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.logging.Logger;

@SpringBootApplication
public class PioneerMain {

    private static Logger logger = Logger.getLogger(PioneerMain.class.getName());

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(PioneerMain.class, args);
    }
}
