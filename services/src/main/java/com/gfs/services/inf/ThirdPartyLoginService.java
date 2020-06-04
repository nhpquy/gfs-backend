package com.gfs.services.inf;

import com.gfs.domain.request.LoginWithFacebookRequest;
import com.gfs.domain.request.LoginWithGoogleRequest;
import com.gfs.domain.response.AccountLoginResponse;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface ThirdPartyLoginService {
    public AccountLoginResponse loginWithFacebook(@Valid LoginWithFacebookRequest request, String profile);

    public AccountLoginResponse loginWithGoogle(@Valid LoginWithGoogleRequest request, String profile);
}
