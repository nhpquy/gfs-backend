package com.gfs.services.annotation;


import com.gfs.domain.enums.AccountProfile;
import com.gfs.domain.enums.AuthorizeTokenRole;
import com.gfs.services.validator.AccountAuthorizedValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AccountAuthorizedValidator.class)
@Documented
public @interface AccountAuthorized {
    String message() default "Unauthorized";

    String name() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    AuthorizeTokenRole[] roles() default {AuthorizeTokenRole.authorized};

    AccountProfile[] profiles() default {};
}
