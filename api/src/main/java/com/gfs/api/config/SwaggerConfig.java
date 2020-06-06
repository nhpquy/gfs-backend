package com.gfs.api.config;

import com.fasterxml.classmate.TypeResolver;
import com.gfs.domain.constant.SwaggerTag;
import com.gfs.domain.model.CurrentAccountLogin;
import com.gfs.domain.model.CurrentStudentLogin;
import com.gfs.domain.model.CurrentTutorLogin;
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
                .apis(RequestHandlerSelectors.basePackage("com.gfs.api.controller"))
                .build()
                .tags(new Tag(SwaggerTag.registration, "APIs for creating account"),
                        new Tag(SwaggerTag.activation, "APIs for activating account"),
                        new Tag(SwaggerTag.login, "APIs for logging in/out"),
                        new Tag(SwaggerTag.password, "APIs for managing password"),
                        new Tag(SwaggerTag.account, "APIs for account"),
                        new Tag(SwaggerTag.tutor, "APIs for managing Tutor's profile"),
                        new Tag(SwaggerTag.student, "APIs for managing Student's profile"),
                        new Tag(SwaggerTag.storage, "APIs for managing files"),
                        new Tag(SwaggerTag.up, "APIs for verifying deployment")
                )
                .apiInfo(swaggerApiInfo())
                .alternateTypeRules(alternateTypeRules(typeResolver))
                .forCodeGeneration(true);
    }

    private AlternateTypeRule[] alternateTypeRules(TypeResolver typeResolver) {
        AlternateTypeRule alternateTypeRule1 = new AlternateTypeRule(typeResolver.resolve(CurrentAccountLogin.class), typeResolver.resolve(String.class));
        AlternateTypeRule alternateTypeRule2 = new AlternateTypeRule(typeResolver.resolve(CurrentStudentLogin.class), typeResolver.resolve(String.class));
        AlternateTypeRule alternateTypeRule3 = new AlternateTypeRule(typeResolver.resolve(CurrentTutorLogin.class), typeResolver.resolve(String.class));
        return new AlternateTypeRule[]{alternateTypeRule1, alternateTypeRule2, alternateTypeRule3};
    }

    private ApiInfo swaggerApiInfo() {
        return new ApiInfoBuilder().title("GFS Project")
                .contact(new Contact("JAVAvengers", "https://javavengers.gfs.vn", "javavengers@gfs.vn"))
                .description("GFS project")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }
}
