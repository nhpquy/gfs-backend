package com.gfs.domain.annotations;

import com.gfs.domain.constant.AnnotationTemplate;
import org.springframework.core.annotation.AliasFor;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

@Pattern(regexp = AnnotationTemplate.PASSWORD_TEMPLATE, message = "Invalid password")
@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
public @interface Password {
    /**
     * @return array of {@code Flag}s considered when resolving the regular expression
     */
    @AliasFor(annotation = Pattern.class)
    Pattern.Flag[] flags() default {};

    /**
     * @return the error message template
     */
    @AliasFor(annotation = Pattern.class)
    String message() default "{javax.validation.constraints.Pattern.message}";

    /**
     * @return the groups the constraint belongs to
     */
    @AliasFor(annotation = Pattern.class)
    Class<?>[] groups() default {};

    /**
     * @return the payload associated to the constraint
     */
    @AliasFor(annotation = Pattern.class)
    Class<? extends Payload>[] payload() default {};
}
