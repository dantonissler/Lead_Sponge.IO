package br.com.blinkdev.leadsponge;

import br.com.blinkdev.leadsponge.config.property.LeadSpongeApiProperty;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(LeadSpongeApiProperty.class)
public class LeadSpongeApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(LeadSpongeApiApplication.class, args);
    }

    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(
            @Value("${spring.application.name}") String applicationName) {
        return (registry) -> registry.config().commonTags("application", applicationName);
    }
}
