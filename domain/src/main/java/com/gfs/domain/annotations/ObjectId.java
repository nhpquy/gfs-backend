package com.gfs.domain.annotations;


import com.gfs.domain.validator.ObjectIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ObjectIdValidator.class)
@Documented
public @interface ObjectId {
    String message() default "Invalid hex id";

    String name() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
