package com.gfs.domain.validator;

import com.gfs.domain.annotations.StringNotEmpty;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class StringNotEmptyValidator implements ConstraintValidator<StringNotEmpty, String> {
    private String message;

    @Override
    public void initialize(StringNotEmpty constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String target, ConstraintValidatorContext constraintValidatorContext) {
        try {
            //disable existing violation message
            constraintValidatorContext.disableDefaultConstraintViolation();

            if (target == null) {
                return true;
            }

            return target.trim().length() > 0;

        } catch (Exception e) {
            return false;
        } finally {
            constraintValidatorContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        }
    }
}
