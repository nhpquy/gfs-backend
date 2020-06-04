package com.gfs.domain.response;

import com.gfs.domain.document.TutorAccount;
import lombok.Data;

@Data
public class AdminTutorAccountInfoResponse extends TutorAccountInfoResponse {

    public AdminTutorAccountInfoResponse(TutorAccount account) {
        super(account);
    }
}
