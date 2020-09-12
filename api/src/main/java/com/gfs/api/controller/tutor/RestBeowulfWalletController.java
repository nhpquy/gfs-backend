package com.gfs.api.controller.tutor;

import com.gfs.api.annotation.ApiV1RestController;
import com.gfs.domain.constant.SwaggerTag;
import com.gfs.domain.enums.OrgRole;
import com.gfs.domain.model.CurrentTutorLogin;
import com.gfs.domain.request.CreateBeowulfWalletRequest;
import com.gfs.domain.response.BeowulfWalletResponse;
import com.gfs.services.annotation.TutorAccountAuthorized;
import com.gfs.services.inf.BeowulfWalletService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@ApiV1RestController(value = "tutor/org/bwf_wallet")
@Api(tags = SwaggerTag.bwf_wallet)
public class RestBeowulfWalletController {

    @Autowired
    BeowulfWalletService beowulfWalletService;

    @ApiOperation(value = "Create new Beowulf Wallet", notes = "'limit' = 2")
    @PostMapping(value = "/create")
    public BeowulfWalletResponse createBeowulfWallet(@TutorAccountAuthorized(orgRoles = {OrgRole.owner})
                                                     @RequestHeader(name = "Authorization") CurrentTutorLogin CurrentTutorLogin,
                                                     @RequestBody CreateBeowulfWalletRequest request) {
        return beowulfWalletService.createBeowulfWallet(request, CurrentTutorLogin);
    }

    @ApiOperation(value = "Check name of wallet used or not?")
    @PostMapping(value = "name/check")
    public boolean isAccountNameValid(@TutorAccountAuthorized(orgRoles = {OrgRole.readonly})
                                      @RequestHeader(name = "Authorization") CurrentTutorLogin CurrentTutorLogin,
                                      @RequestParam("name") String name) {
        return beowulfWalletService.checkAccountName(name);
    }

    @ApiOperation(value = "Get List Beowulf wallets")
    @PostMapping(value = "/list")
    public List<BeowulfWalletResponse> getListBeowulfWallets(@TutorAccountAuthorized(orgRoles = {OrgRole.readonly})
                                                             @RequestHeader(name = "Authorization") CurrentTutorLogin CurrentTutorLogin) {
        return beowulfWalletService.getListBeowulfWallets(CurrentTutorLogin);
    }
}
