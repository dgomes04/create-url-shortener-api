package com.zeeq.createUrlShortener.services.impl;

import com.zeeq.createUrlShortener.services.S3StorageService;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.UUID;

public class S3StorageServiceImpl implements S3StorageService {
    private final S3Client s3Client = S3Client.builder().build();

    @Override
    public String saveObject(String urlDataJson) {
        var shortUrlCode = UUID.randomUUID().toString().substring(0, 8);
        PutObjectRequest request = createS3Request(shortUrlCode);
        s3Client.putObject(request, RequestBody.fromString(urlDataJson));
        return shortUrlCode;
    }

    private static PutObjectRequest createS3Request(String shortUrlCode) {
        return PutObjectRequest.builder()
                .bucket("zeeq-url-shortener-storage")
                .key(shortUrlCode + ".json")
                .build();
    }
}
