package com.gfs.admin.config;

import com.fasterxml.classmate.TypeResolver;
import com.gfs.domain.enums.admin.AdminSwaggerTag;
import com.gfs.domain.model.CurrentAdminLogin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    @Bean
    public Docket swaggerDocs(TypeResolver typeResolver) {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.gfs.admin.controller"))
                .build()
                .tags(new Tag(AdminSwaggerTag.admin_manager, "APIs for managing admin"),
                        new Tag(AdminSwaggerTag.admin_account, "APIs for admin account"),
                        new Tag(AdminSwaggerTag.login, "APIs for logging in admin account"),
                        new Tag(AdminSwaggerTag.tutor, "APIs for managing tutor"),
                        new Tag(AdminSwaggerTag.student, "APIs for managing student")
                )
                .apiInfo(swaggerApiInfo())
                .alternateTypeRules(alternateTypeRules(typeResolver))
                .forCodeGeneration(true);
    }

    private AlternateTypeRule[] alternateTypeRules(TypeResolver typeResolver) {
        AlternateTypeRule alternateTypeRule1 = new AlternateTypeRule(typeResolver.resolve(CurrentAdminLogin.class), typeResolver.resolve(String.class));
        return new AlternateTypeRule[]{alternateTypeRule1};
    }

    private ApiInfo swaggerApiInfo() {
        return new ApiInfoBuilder().title("GFS Admin APIs")
                .contact(new Contact("JAVAvengers", "https://javavengers.gfs.vn", "javavengers@gfs.vn"))
                .description("GFS project")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }
}
