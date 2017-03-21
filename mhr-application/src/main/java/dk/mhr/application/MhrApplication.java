package dk.mhr.application;

import dk.mhr.ihc.persistence.LightEvent;
import dk.mhr.ihc.persistence.LightRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.logging.Logger;

/**
 * Created by mortenrummelhoff on 06/03/16.
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "dk.mhr.ihc")
@EntityScan(basePackageClasses=LightEvent.class)
@EnableTransactionManagement
@ComponentScan(basePackages = {"dk.mhr.pioneer", "dk.mhr.ihc",  "dk.mhr.application.security"})
public class MhrApplication {

    private static Logger logger = Logger.getLogger(MhrApplication.class.getName());

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(MhrApplication.class, args);
    }

}
