package com.gfs.services.impl;

import com.gfs.domain.utils.LoggerUtil;
import com.gfs.domain.utils.S3AWSUtils;
import com.gfs.services.inf.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadFile(MultipartFile file, String fileName) {
        try {
            return S3AWSUtils.uploadFile(file.getInputStream(), fileName, file.getContentType());
        } catch (Exception e) {
            LoggerUtil.e(this, e.getMessage());
        }
        return null;
    }
}
