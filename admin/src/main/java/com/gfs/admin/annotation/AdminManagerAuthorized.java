package com.gfs.admin.annotation;


import com.gfs.admin.validator.AdminManagerAuthorizedValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AdminManagerAuthorizedValidator.class)
@Documented
public @interface AdminManagerAuthorized {
    String message() default "Unauthorized";

    String name() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
