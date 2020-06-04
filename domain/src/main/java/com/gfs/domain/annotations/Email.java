package com.gfs.domain.annotations;

import com.gfs.domain.constant.AnnotationTemplate;
import org.springframework.core.annotation.AliasFor;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;


@Pattern(regexp = AnnotationTemplate.EMAIL_TEMPLATE, message = "Invalid email address")
@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { })
public @interface Email {
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
