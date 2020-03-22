package com.shortener.url.example.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.shortener.url.example.repositories.UrlMappingRepository;
import com.shortener.url.example.model.UrlMapping;

@Service
public class UrlService {
    @Autowired
    private UrlMappingRepository urlMappingRepository;

    public void createUrl(String key, String url) {
        urlMappingRepository.insert(new UrlMapping(key, url));
    }
    public String getUrlByKey(String key) {
        return urlMappingRepository.findById(key).orElseThrow(NullPointerException::new).getUrl();
    }
    public boolean keyExists(String key) {
        return urlMappingRepository.existsById(key);
    }
}