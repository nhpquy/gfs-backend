package com.gfs.services.annotation;


import com.gfs.domain.enums.AuthorizeTokenRole;
import com.gfs.domain.enums.OrgRole;
import com.gfs.services.validator.TutorAccountAuthorizedValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TutorAccountAuthorizedValidator.class)
@Documented
public @interface TutorAccountAuthorized {
    String message() default "Unauthorized";

    String name() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    AuthorizeTokenRole[] roles() default {AuthorizeTokenRole.authorized};

    OrgRole[] orgRoles() default {OrgRole.none};
}
