package com.shortener.url.example.controllers.url;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import com.shortener.url.example.services.UrlService;

import java.util.Map;

@RestController
public class UrlShortener {
	@Autowired
	private UrlService urlService;

    @PostMapping("/shorten")
	public @ResponseBody ResponseEntity<String> shortenUrl(@RequestBody Map<String, String> urlMapping) {
		try {
			urlService.createUrl(urlMapping.get("key"), urlMapping.get("url"));
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/{key}")
	public @ResponseBody
	ResponseEntity<String> routeUrl(@PathVariable(value="key") String key) throws NullPointerException {
		try {
			return new ResponseEntity<>(urlService.getUrlByKey(key), HttpStatus.OK);
		} catch(NullPointerException exception) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
}