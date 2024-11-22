package com.zeeq.createUrlShortener;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeeq.createUrlShortener.services.CreateUrlShortenerService;
import com.zeeq.createUrlShortener.services.impl.CreateUrlShortenerServiceImpl;
import com.zeeq.createUrlShortener.services.impl.S3StorageServiceImpl;

import java.util.Map;

public class App implements RequestHandler<Map<String, Object>, Map<String, String>> {

    private final CreateUrlShortenerService urlRedirectService = new CreateUrlShortenerServiceImpl(
            new S3StorageServiceImpl(),
            new ObjectMapper()
    );

    @Override
    public Map<String, String> handleRequest(Map<String, Object> input, Context context) {
        return urlRedirectService.handleCreateRequest(input);
    }
}
