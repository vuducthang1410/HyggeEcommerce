package org.vdt.productmanagementservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {
    private final S3Client s3Client;
    @Value("${bucketName}")
    private String bucketName;
    private final Logger logger = LoggerFactory.getLogger(S3Service.class);
    public String uploadFile(MultipartFile file) throws IOException {
        String key = "hehe/" + UUID.randomUUID().toString().concat("-") + file.getOriginalFilename();
        try (InputStream inputStream = file.getInputStream()) {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .contentType(file.getContentType())
                    .contentDisposition("inline")
//                        .metadata(Map.of("content-type",file.getContentType()))
                    .build();
            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, file.getSize()));
            return key;
        } catch (S3Exception e) {
            logger.error("Error uploading file to S3", e);
            return null;
        }
    }
    public boolean deleteFile(String key) {
        try {
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();
            DeleteObjectResponse objectResponse = s3Client.deleteObject(deleteObjectRequest);
            return objectResponse.deleteMarker() != null && objectResponse.deleteMarker();
        } catch (S3Exception e) {
            logger.error("Error while delete : {}", e.getMessage());
            return false;
        }
    }
    public String getLinkFile(String key){
        return "https://"+bucketName+".s3.amazonaws.com/" +key;
    }

}
