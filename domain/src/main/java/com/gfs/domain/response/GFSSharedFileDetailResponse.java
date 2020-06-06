package com.gfs.domain.response;

import com.gfs.domain.document.Account;
import com.gfs.domain.document.GFSSharedFile;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GFSSharedFileDetailResponse extends GFSSharedFileResponse {
    private AccountInfoResponse sender;
    private AccountInfoResponse receiver;

    public GFSSharedFileDetailResponse(GFSSharedFile sharedFile, Account sender, Account receiver) {
        super(sharedFile);
        this.setSender(new AccountInfoResponse(sender));
        this.setReceiver(new AccountInfoResponse(receiver));
    }
}
