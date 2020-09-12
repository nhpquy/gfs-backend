package com.gfs.services.validator;

import com.gfs.domain.document.AccountAuthorizedToken;
import com.gfs.domain.enums.AuthorizeTokenRole;
import com.gfs.domain.enums.OrgRole;
import com.gfs.domain.exception.ServiceException;
import com.gfs.domain.model.CurrentTutorLogin;
import com.gfs.domain.utils.ServiceExceptionUtils;
import com.gfs.services.annotation.TutorAccountAuthorized;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TutorAccountAuthorizedValidator implements ConstraintValidator<TutorAccountAuthorized, CurrentTutorLogin> {

    private List<AuthorizeTokenRole> roles;
    private List<OrgRole> orgRoles;

    @Override
    public void initialize(TutorAccountAuthorized constraintAnnotation) {
        roles = Arrays.stream(constraintAnnotation.roles()).collect(Collectors.toList());
        orgRoles = Arrays.asList(constraintAnnotation.orgRoles());
    }

    @Override
    public boolean isValid(CurrentTutorLogin currentTutorLogin, ConstraintValidatorContext constraintValidatorContext) {
        ServiceException exception = ServiceExceptionUtils.unAuthorize();
        try {
            //disable existing violation message
            constraintValidatorContext.disableDefaultConstraintViolation();

            if (currentTutorLogin == null) {
                return false;
            }

            if (currentTutorLogin.hasError()) {
                exception = currentTutorLogin.getError();
                return false;
            }

            AccountAuthorizedToken authorizedToken = currentTutorLogin.getAuthorizedToken();

            if (currentTutorLogin.getJoinedOrg() == null) {
                exception = ServiceExceptionUtils.organizationNotFound();
                return false;
            }

            OrgRole currentRoleInOrg = currentTutorLogin.getJoinedOrg().getRole();

            if (!roles.contains(AuthorizeTokenRole.all) && !roles.contains(authorizedToken.getRole()))
                return false;

            if (!orgRoles.contains((OrgRole.none)) && orgRoles.stream().noneMatch(role -> currentRoleInOrg.getValue() >= role.getValue())) {
                exception = ServiceExceptionUtils.permissionDenied();
                return false;
            }

            return true;

        } catch (Exception e) {
            return false;
        } finally {
            String message = exception.generateStringResponse();
            constraintValidatorContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        }
    }
}
