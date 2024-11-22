package com.zeeq.createUrlShortener.services;

import java.util.Map;

public interface CreateUrlShortenerService {
    Map<String, String> handleCreateRequest(Map<String, Object> input);
}
