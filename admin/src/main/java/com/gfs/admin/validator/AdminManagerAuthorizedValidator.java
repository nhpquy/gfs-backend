package com.gfs.admin.validator;

import com.gfs.admin.annotation.AdminManagerAuthorized;
import com.gfs.domain.exception.ServiceException;
import com.gfs.domain.utils.SCryptKdfUtil;
import com.gfs.domain.utils.ServiceExceptionUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class AdminManagerAuthorizedValidator implements ConstraintValidator<AdminManagerAuthorized, Object> {

    private static final String AUTHORIZATION = "c2NyeXB0ABAAAAAIAAAAAW3OcuB5xAggZHuaxNFrYAD4uxWQfQtpQaI4gL4QIond3pqclvzi4YuaCOA2Xb/Cn4CQzzv1MO/BSb0bOyBp/pQYD710dhzM/BcxiMdmn1AO";

    @Override
    public boolean isValid(Object target, ConstraintValidatorContext constraintValidatorContext) {
        ServiceException exception = ServiceExceptionUtils.unAuthorize();
        try {
            //disable existing violation message
            constraintValidatorContext.disableDefaultConstraintViolation();

            String authorization = null;

            if (target == null)
                return false;

            if (target instanceof HttpServletRequest)
                authorization = ((HttpServletRequest) target).getHeader("Authorization");
            else if (target instanceof String)
                authorization = (String) target;

            return SCryptKdfUtil.verify(authorization, AUTHORIZATION);

        } catch (Exception e) {
            return false;
        } finally {
            String message = exception.generateStringResponse();
            constraintValidatorContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        }
    }
}
