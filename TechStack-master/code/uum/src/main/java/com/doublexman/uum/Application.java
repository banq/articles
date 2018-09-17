package com.doublexman.uum;

import com.doublexman.uum.config.ApplicationProperties;
import com.doublexman.uum.config.DefaultProfileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@ComponentScan(basePackages = {"com.doublexman.microservice"})
@EnableAutoConfiguration
@EnableConfigurationProperties({ApplicationProperties.class, LiquibaseProperties.class})
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    private final Environment env;

    public Application(Environment env) {
        this.env = env;
    }

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(Application.class);
        // add default profile (if no environment variable "spring.profiles.active", default will be used)
        /**
         * set default profile to 'dev'
         * If no profile is activated, default profile will be loaded
         */
        DefaultProfileUtil.addDefaultProfile(app);

        Environment env = app.run(args).getEnvironment();
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        logger.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\t{}://localhost:{}\n\t" +
                        "Swagger: \t{}://localhost:{}/swagger-ui.html\n\t" +
                        "External: \t{}://{}:{}\n\t" +
                        "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                protocol,
                env.getProperty("server.port"),
                protocol,
                env.getProperty("server.port"),
                protocol,
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getActiveProfiles());
    }


}
