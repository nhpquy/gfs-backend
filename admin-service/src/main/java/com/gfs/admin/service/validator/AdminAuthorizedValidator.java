package com.gfs.admin.service.validator;

import com.gfs.admin.service.annotation.AdminAuthorized;
import com.gfs.domain.document.AccountAuthorizedToken;
import com.gfs.domain.document.AdminAccount;
import com.gfs.domain.enums.admin.AdminRole;
import com.gfs.domain.enums.AuthorizeTokenRole;
import com.gfs.domain.exception.ServiceException;
import com.gfs.domain.model.CurrentAdminLogin;
import com.gfs.domain.utils.ServiceExceptionUtils;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdminAuthorizedValidator implements ConstraintValidator<AdminAuthorized, CurrentAdminLogin> {

    private List<AdminRole> adminRoles;
    private List<AuthorizeTokenRole> tokenRoles;

    @Override
    public void initialize(AdminAuthorized constraintAnnotation) {
        adminRoles = Arrays.stream(constraintAnnotation.adminRoles()).collect(Collectors.toList());
        tokenRoles = Arrays.stream(constraintAnnotation.tokenRoles()).collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CurrentAdminLogin currentAdminLogin, ConstraintValidatorContext constraintValidatorContext) {
        ServiceException exception = ServiceExceptionUtils.unAuthorize();
        try {
            //disable existing violation message
            constraintValidatorContext.disableDefaultConstraintViolation();

            if (currentAdminLogin == null) {
                return false;
            }

            if (currentAdminLogin.hasError()) {
                exception = currentAdminLogin.getError();
                return false;
            }

            AdminAccount account = currentAdminLogin.getAccount();
            AccountAuthorizedToken authorizedToken = currentAdminLogin.getAuthorizedToken();

            if (!adminRoles.contains(AdminRole.all) && !adminRoles.contains(account.getRole()))
                return false;

            if (!tokenRoles.contains(AuthorizeTokenRole.all) && !tokenRoles.contains(authorizedToken.getRole()))
                return false;

            return true;

        } catch (Exception e) {
            return false;
        } finally {
            String message = exception.generateStringResponse();
            constraintValidatorContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        }
    }
}
