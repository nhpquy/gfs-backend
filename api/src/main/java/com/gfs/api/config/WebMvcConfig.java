package com.gfs.api.config;

import com.gfs.domain.config.ApplicationProperties;
import com.gfs.domain.utils.LoggerUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        LoggerUtil.d(this, "Config cross origin: " + String.join(",", ApplicationProperties.getAllowedCrossDomains()));
        registry.addMapping("/**")
                .allowedOrigins(ApplicationProperties.getAllowedCrossDomains()
                        .toArray(new String[0]))
                .allowCredentials(true)
                .allowedHeaders("X-Requested-With", "X-Auth-Token", "Content-Type", "Access-Control-Allow-Origin", "Authorization")
                .allowedMethods("POST", "GET", "HEAD", "OPTIONS", "DELETE", "PUT");
    }
}
