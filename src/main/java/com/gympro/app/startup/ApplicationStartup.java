package com.gympro.app.startup;

import com.google.common.base.Throwables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Profile("dev")
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationStartup.class);
    @Autowired
    private DataSource dataSource;
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            ScriptUtils.executeSqlScript(dataSource.getConnection(), new DefaultResourceLoader().getResource("sampleData.sql"));
        } catch (Exception e) {
            LOGGER.error(Throwables.getStackTraceAsString(e));
        }

    }
}
