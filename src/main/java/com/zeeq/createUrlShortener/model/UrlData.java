package com.zeeq.createUrlShortener.model;

import lombok.Builder;

@Builder
public record UrlData(String originalUrl, Long expirationTime) {
}
