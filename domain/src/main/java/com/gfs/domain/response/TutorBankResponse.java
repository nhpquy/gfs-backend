package com.gfs.domain.response;

import com.gfs.domain.model.TutorBank;
import lombok.Data;

@Data
public class TutorBankResponse {
    private String name;
    private String bank;
    private String number;
    private String status;

    public TutorBankResponse() {
    }

    public TutorBankResponse(String name, String bank, String number, String status) {
        this.name = name;
        this.bank = bank;
        this.number = number;
        this.status = status;
    }

    public TutorBankResponse(TutorBank bank) {
        this(bank.getName(),
                bank.getBank(),
                bank.getNumber(),
                bank.getStatus());
    }
}
