package org.gesart.gesart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;


@SuppressWarnings("ALL")
@Slf4j
@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.gesart.gesart.repository")
public class GesartApplication {
    /**
     * Main.
     * @param args
     */
    public static void main(final String[] args) {
        SpringApplication app = new SpringApplication(GesartApplication.class);
        Environment env = app.run(args).getEnvironment();
        logApplicationStartup(env);
    }
    private static void logApplicationStartup(final Environment env) {
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        String serverPort = env.getProperty("server.port");
        String contextPath = env.getProperty("server.servlet.context-path");
        if (StringUtils.isEmpty(contextPath)) {
            contextPath = "/";
        }
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            System.out.println("The host name could not be determined, using `localhost` as fallback");
        }
        System.out.println("\n----------------------------------------------------------\n\t"
                + "Application " + env.getProperty("spring.application.name") + " is running! Access URLs:\n\t"
                + "Local: \t\t" + protocol + "://localhost:" + serverPort + "" + contextPath + "\n\t"
                + "External: \t" + protocol + "://" + hostAddress + ":" + serverPort + "" + contextPath + "\n\t"
                + "Profile(s): \t" + Arrays.toString(env.getActiveProfiles())
                + "\n----------------------------------------------------------");
    }
}