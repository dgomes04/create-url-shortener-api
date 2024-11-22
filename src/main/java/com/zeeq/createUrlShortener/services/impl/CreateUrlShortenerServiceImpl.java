package com.zeeq.createUrlShortener.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeeq.createUrlShortener.model.UrlData;
import com.zeeq.createUrlShortener.services.CreateUrlShortenerService;
import com.zeeq.createUrlShortener.services.S3StorageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CreateUrlShortenerServiceImpl implements CreateUrlShortenerService {
    private static final Logger log = LogManager.getLogger(CreateUrlShortenerServiceImpl.class);
    private final S3StorageService s3StorageService;
    private final ObjectMapper objectMapper;

    public CreateUrlShortenerServiceImpl(S3StorageService s3StorageService, ObjectMapper objectMapper) {
        this.s3StorageService = s3StorageService;
        this.objectMapper = objectMapper;
    }

    @Override
    public Map<String, String> handleCreateRequest(Map<String, Object> input) {
        var urlData = createUrlData(input);
        Map<String, String> response = new HashMap<>();
        try {
            var urlDataJson = parseToString(urlData);
            var shortUrlCode = s3StorageService.saveObject(urlDataJson);
            response.put("code", shortUrlCode);
            return response;
        } catch (Exception exception) {
            throw new RuntimeException("Error saving data to S3: " + exception.getMessage(), exception);
        }
    }


    private UrlData createUrlData(Map<String, Object> input) {
        String body = input.get("body").toString();
        var bodyMap = parseBodyMap(body);
        String originalUrl = bodyMap.get("originalUrl");
        String expirationTime = bodyMap.get("expirationTime");
        return new UrlData(originalUrl, Long.parseLong(expirationTime));
    }

    private Map<String, String> parseBodyMap(String body) {
        Map<String, String> bodyMap;
        try {
            bodyMap = objectMapper.readValue(body, Map.class);
            return bodyMap;
        } catch (Exception exception) {
            throw new RuntimeException("Error parsing JSON body: " + exception.getMessage(), exception);
        }
    }

    private String parseToString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Error parsing object to string", e);
            return null;
        }
    }
}
