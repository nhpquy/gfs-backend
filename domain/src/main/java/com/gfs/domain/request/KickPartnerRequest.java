package com.gfs.domain.request;

import com.gfs.domain.annotations.StringNotEmpty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class KickPartnerRequest {
    @NotNull(message = "'account_id' must not be null")
    @StringNotEmpty(message = "'account_id' must not be empty")
    private String account_id;
}
