package com.zeeq.createUrlShortener.services;

public interface S3StorageService {
    String saveObject(String urlDataJson);
}
