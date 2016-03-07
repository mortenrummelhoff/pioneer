package dk.mhr.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.logging.Logger;

/**
 * Created by mortenrummelhoff on 06/03/16.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"dk.mhr.pioneer", "dk.mhr.ihc"})
public class MhrApplication {

    private static Logger logger = Logger.getLogger(MhrApplication.class.getName());

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(MhrApplication.class, args);
    }

}
