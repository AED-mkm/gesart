package org.gesart.gesart.config;
import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

import javax.sql.DataSource;


@SuppressWarnings("ALL")
@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties(LiquibaseProperties.class)
public class LiquibaseConfig {

    private final Logger log = LoggerFactory.getLogger(LiquibaseConfig.class);

    private final Environment env;

    /**
     * Config constructor.
     *
     * @param env
     */
    public LiquibaseConfig(final Environment env) {
        this.env = env;
    }

    /**
     * Config bean.
     *
     * @param dataSource
     * @param liquibaseProperties
     * @return {@link SpringLiquibase}
     */
    @Bean
    public SpringLiquibase liquibase(final DataSource dataSource, final LiquibaseProperties liquibaseProperties) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:db/liquibase/master.xml");
        liquibase.setContexts(liquibaseProperties.getContexts());
        liquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
        liquibase.setDropFirst(liquibaseProperties.isDropFirst());
        liquibase.setChangeLogParameters(liquibaseProperties.getParameters());
        if (env.acceptsProfiles(Profiles.of("no-liquibase"))) {
            liquibase.setShouldRun(false);
        } else {
            liquibase.setShouldRun(liquibaseProperties.isEnabled());
            log.info("Configuring Liquibase");
        }
        return liquibase;
    }
}
