package com.gfs.services.inf;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Validated
public interface FileService {
    public String uploadFile(@NotNull MultipartFile file, @NotNull @NotEmpty String fileName);
}
