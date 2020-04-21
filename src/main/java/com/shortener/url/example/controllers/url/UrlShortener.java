package com.shortener.url.example.controllers.url;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import com.shortener.url.example.services.UrlService;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UrlShortener {
	@Autowired
	private UrlService urlService;

	@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/shorten")
	public ResponseEntity<String> shortenUrl(@RequestBody Map<String, String> urlMapping) {
		try {
			urlService.createUrl(urlMapping.get("alias"), urlMapping.get("url"));
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/{key}")
	public RedirectView routeUrl(@PathVariable(value="key") String key) throws NullPointerException {
		try {
			return new RedirectView(urlService.getUrlByKey(key));
		} catch(NullPointerException exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/url-validation")
	public @ResponseBody Map<String, String> validateUrl(@RequestBody Map<String, String> url) throws Exception {
		if (urlService.validateUrl(url.get("url"))) {
			return new HashMap<String, String>(){{ put("status", String.valueOf(HttpStatus.OK.value()) ); }};
		}
		else {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}