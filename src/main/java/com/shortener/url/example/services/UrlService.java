package com.shortener.url.example.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.shortener.url.example.repositories.UrlMappingRepository;
import com.shortener.url.example.model.UrlMapping;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.IllegalFormatException;
import java.util.prefs.InvalidPreferencesFormatException;

@Service
public class UrlService {
    @Autowired
    private UrlMappingRepository urlMappingRepository;

    public void createUrl(final String key, final String url) {
        urlMappingRepository.insert(new UrlMapping(key, url));
    }
    public String getUrlByKey(String key) {
        return urlMappingRepository.findById(key).orElseThrow(NullPointerException::new).getUrl();
    }
    public boolean keyExists(String key) {
        return urlMappingRepository.existsById(key);
    }

    public boolean validateUrl(final String url) throws MalformedURLException, URISyntaxException {
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException exception) {
            return false;
        }
    }
}