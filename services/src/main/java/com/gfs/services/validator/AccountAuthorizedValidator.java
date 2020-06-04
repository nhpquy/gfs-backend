package com.gfs.services.validator;

import com.gfs.domain.document.Account;
import com.gfs.domain.document.AccountAuthorizedToken;
import com.gfs.domain.enums.AccountProfile;
import com.gfs.domain.enums.AuthorizeTokenRole;
import com.gfs.domain.exception.ServiceException;
import com.gfs.domain.model.CurrentAccountLogin;
import com.gfs.domain.utils.ServiceExceptionUtils;
import com.gfs.services.annotation.AccountAuthorized;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountAuthorizedValidator implements ConstraintValidator<AccountAuthorized, CurrentAccountLogin> {

    private List<AuthorizeTokenRole> roles;
    private List<AccountProfile> profiles;

    @Override
    public void initialize(AccountAuthorized constraintAnnotation) {
        roles = Arrays.stream(constraintAnnotation.roles()).collect(Collectors.toList());
        profiles = Arrays.asList(constraintAnnotation.profiles());
    }

    @Override
    public boolean isValid(CurrentAccountLogin currentAccountLogin, ConstraintValidatorContext constraintValidatorContext) {
        ServiceException exception = ServiceExceptionUtils.unAuthorize();
        try {
            //disable existing violation message
            constraintValidatorContext.disableDefaultConstraintViolation();

            if (currentAccountLogin == null) {
                return false;
            }

            if (currentAccountLogin.hasError()) {
                exception = currentAccountLogin.getError();
                return false;
            }

            Account account = currentAccountLogin.getAccount();
            AccountAuthorizedToken authorizedToken = currentAccountLogin.getAuthorizedToken();

            if (!roles.contains(AuthorizeTokenRole.all) && !roles.contains(authorizedToken.getRole()))
                return false;

            if (!profiles.contains(account.getProfile_type()))
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
