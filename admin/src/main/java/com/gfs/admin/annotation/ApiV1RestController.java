package com.gfs.admin.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
@RequestMapping
@Validated
@RequestMappingPrefix("v1")
public @interface ApiV1RestController {

    @AliasFor(annotation = RequestMapping.class)
    String[] value() default {};
}
