package com.gfs.domain.request;

import com.gfs.domain.annotations.Email;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ShareFileRequest {
    @Email
    @NotNull(message = "'receiver_email' must not be null")
    private String receiver_email;

    @NotNull(message = "'transaction_id' must not be null")
    @NotEmpty(message = "'transaction_id' must not be empty")
    private String transaction_id;

    private String description;
}
