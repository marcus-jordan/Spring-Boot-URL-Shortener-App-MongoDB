package com.shortener.url.example.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "urlmappings")
public class UrlMapping {
    @Id
    private String key;
    private String url;

    public UrlMapping(String key, String url) {
        this.key = key;
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
    public String getKey() {
        return key;
    }
    @Override
    public String toString() {
        return String.format("Url Mapping [key='%s', url='%s']", key, url);
    }
}