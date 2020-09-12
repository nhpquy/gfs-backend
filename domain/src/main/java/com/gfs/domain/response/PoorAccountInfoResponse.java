package com.gfs.domain.response;

import com.gfs.domain.document.Account;
import com.gfs.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoorAccountInfoResponse extends DocumentResponse {
    private String email;
    private String name;
    private Gender gender;

    public PoorAccountInfoResponse(Account account) {
        super(account);
        this.setEmail(account.getEmail());
        this.setName(account.getName());
        this.setGender(account.getGender());
    }
}
