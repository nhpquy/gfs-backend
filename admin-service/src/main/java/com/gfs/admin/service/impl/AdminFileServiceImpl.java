package com.gfs.admin.service.impl;

import com.gfs.admin.service.inf.AdminFileService;
import com.gfs.domain.utils.LoggerUtil;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class AdminFileServiceImpl implements AdminFileService {

    @Override
    public String uploadFile(MultipartFile file, String fileName) {
        try {
            IPFS ipfs = new IPFS("localhost", 5001);
            NamedStreamable.FileWrapper sFile = new NamedStreamable.FileWrapper(convert(file));
            MerkleNode addResult = ipfs.add(sFile).get(0);
            return addResult.toJSONString();
        } catch (Exception e) {
            LoggerUtil.e(this, e.getMessage());
        }
        return null;
    }

    private File convert(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return convFile;
    }
}
