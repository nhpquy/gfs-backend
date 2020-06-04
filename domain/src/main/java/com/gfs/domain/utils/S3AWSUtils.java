package com.gfs.domain.utils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.gfs.domain.config.ApplicationProperties;
import com.gfs.domain.constant.AwsConstant;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

public class S3AWSUtils {
    private static final String TAG = S3AWSUtils.class.getName();

    private static String genFileKey(String fileName) {
        return TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis()) + "/" + MD5.hash(String.valueOf(System.currentTimeMillis())) + "/" + fileName;
    }

    public static String uploadFile(InputStream inputStream, String fileName, String contentType) {
        return uploadFile(inputStream, genFileKey(fileName), ApplicationProperties.getS3BucketName(), contentType);
    }

    public static String uploadFile(InputStream inputStream, String fileName, String bucket_name, String contentType) {
        try {
            File tempFile = File.createTempFile(AwsConstant.S3_TEMP_FILE_PREFIX, AwsConstant.S3_TEMP_FILE_SUFFIX);
            tempFile.deleteOnExit();
            OutputStream outputStream = new FileOutputStream(tempFile);
            IOUtils.copy(inputStream, outputStream);
            outputStream.close();
            AWSCredentials awsCredentials = new BasicAWSCredentials(ApplicationProperties.getS3AccessKey(), ApplicationProperties.getS3SecretKey());
            AmazonS3 s3client = new AmazonS3Client(awsCredentials);

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket_name, fileName, tempFile);
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(contentType);
            putObjectRequest.setMetadata(objectMetadata);
            s3client.putObject(putObjectRequest); //.withCannedAcl(CannedAccessControlList.PublicRead));

            return String.format("https://%s.%s/%s", bucket_name, AwsConstant.S3_LINK, fileName);

        } catch (Exception ex) {
            LoggerUtil.exception(TAG, ex, true);
            return null;
        }
    }
}
