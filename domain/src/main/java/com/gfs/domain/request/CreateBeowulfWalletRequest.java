package com.gfs.domain.request;

import com.gfs.domain.annotations.StringNotEmpty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateBeowulfWalletRequest {
    @NotNull(message = "'name' must not be null")
    @StringNotEmpty(message = "'name' must not be empty")
    private String name;

    private String description;
}
