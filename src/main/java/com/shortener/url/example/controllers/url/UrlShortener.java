package com.shortener.url.example.controllers.url;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import com.shortener.url.example.services.UrlService;
import java.util.Map;

@RestController
public class UrlShortener {
	@Autowired
	private UrlService urlService;

    @PostMapping("/shorten")
	public void shortenUrl(@RequestBody Map<String, String> urlMapping) {
		urlService.createUrl(urlMapping.get("key"), urlMapping.get("url"));
	}
}