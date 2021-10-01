package br.com.blinkdev.leadsponge;

import br.com.blinkdev.leadsponge.config.property.LeadSpongeApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableConfigurationProperties(LeadSpongeApiProperty.class)
public class LeadSpongeApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(LeadSpongeApiApplication.class, args);
    }

}
