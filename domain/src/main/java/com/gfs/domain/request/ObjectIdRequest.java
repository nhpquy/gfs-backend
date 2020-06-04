package com.gfs.domain.request;

import com.gfs.domain.annotations.ObjectId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjectIdRequest {
    @ObjectId
    @NotNull(message = "'_id' must not be null")
    @NotEmpty(message = "'_id' must not be empty")
    private String id;
}
