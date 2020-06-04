package com.gfs.admin.service.annotation;


import com.gfs.admin.service.validator.AdminAuthorizedValidator;
import com.gfs.domain.enums.admin.AdminRole;
import com.gfs.domain.enums.AuthorizeTokenRole;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AdminAuthorizedValidator.class)
@Documented
public @interface AdminAuthorized {
    String message() default "Unauthorized";

    String name() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    AdminRole[] adminRoles() default {AdminRole.super_admin};

    AuthorizeTokenRole[] tokenRoles() default {AuthorizeTokenRole.authorized};

}
