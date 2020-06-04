package com.gfs.domain.response;

import com.gfs.domain.document.Account;
import com.gfs.domain.document.TutorAccount;
import lombok.Data;

import java.util.List;

@Data
public class TutorAccountInfoResponse extends AccountInfoResponse {
    private String short_description;
    private String certificate;
    private String experience; // 10 năm
    private List<String> specializes; // chuyên môn ["Toán","Lý","Hoá"]
    private TutorBankResponse bank;

    public TutorAccountInfoResponse() {
    }

    public TutorAccountInfoResponse(Account account) {
        super(account);
    }

    public TutorAccountInfoResponse(TutorAccount account) {
        super(account);
        this.setShort_description(account.getShort_description());
        this.setCertificate(account.getCertificate());
        this.setExperience(account.getExperience());
        this.setSpecializes(account.getSpecializes());
        this.setBank(account.getBank() != null ? new TutorBankResponse(account.getBank()) : null);
    }
}
