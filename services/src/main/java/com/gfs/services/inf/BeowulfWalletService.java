package com.gfs.services.inf;

import com.gfs.domain.model.CurrentTutorLogin;
import com.gfs.domain.request.CreateBeowulfWalletRequest;
import com.gfs.domain.response.BeowulfWalletResponse;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface BeowulfWalletService {
    public BeowulfWalletResponse createBeowulfWallet(@Valid CreateBeowulfWalletRequest request, CurrentTutorLogin currentTutorLogin);

    public boolean checkAccountName(String accountName);

    public List<BeowulfWalletResponse> getListBeowulfWallets(CurrentTutorLogin currentTutorLogin);

    public String pushTransaction(String from, String to, Object extendData);
}
