package com.gfs.api;

import com.gfs.domain.utils.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@ComponentScan(value = "com.gfs")
public class GFSApiV1Application {
    private static final String TAG = GFSApiV1Application.class.getName();
    private static final Logger logger = LoggerFactory.getLogger(TAG);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(GFSApiV1Application.class);
        Map<String, Object> configs = new HashMap<>();
        configs.put("server.servlet.context-path", "/api");
        configs.put("server.port", "8082");
        configs.put("security.ignored", "/**");
        app.setDefaultProperties(configs);
        app.run(args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(final ApplicationContext context) {
        return args -> {
            LoggerUtil.i(TAG, "Let's inspect the beans provided by Spring Boot:");
            String[] beanNames = context.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                logger.info(beanName);
            }
        };
    }

    @Bean //this could be provided via auto-configuration
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}
