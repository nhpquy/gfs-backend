package com.gfs.domain.validator;

import com.gfs.domain.annotations.ObjectId;
import com.gfs.domain.exception.ServiceException;
import com.gfs.domain.utils.ServiceExceptionUtils;
import com.gfs.domain.utils.StringUtils;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ObjectIdValidator implements ConstraintValidator<ObjectId, Object> {

    @Override
    public boolean isValid(Object target, ConstraintValidatorContext constraintValidatorContext) {
        ServiceException exception = ServiceExceptionUtils.invalidParam("Hex id is invalid");
        try {
            //disable existing violation message
            constraintValidatorContext.disableDefaultConstraintViolation();

            if (target == null) {
                return true;
            }

            if (target instanceof org.bson.types.ObjectId)
                return true;

            if (target instanceof String) {
                if (!StringUtils.hasText((String) target)) {
                    return true;
                }
                return org.bson.types.ObjectId.isValid((String) target);
            }
            return false;

        } catch (Exception e) {
            return false;
        } finally {
            String message = exception.generateStringResponse();
            constraintValidatorContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        }
    }
}
