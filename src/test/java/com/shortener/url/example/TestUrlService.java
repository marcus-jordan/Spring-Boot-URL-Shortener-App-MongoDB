package com.shortener.url.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import com.shortener.url.example.repositories.UrlMappingRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.boot.test.context.SpringBootTest;
import com.shortener.url.example.services.UrlService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles(profiles = {"test"})
class TestUrlService {

	private UrlMappingRepository urlMappingRepository;
	private UrlService urlService;

	@Autowired
	TestUrlService(UrlMappingRepository urlMappingRepository, UrlService urlService) {
		this.urlMappingRepository = urlMappingRepository;
		this.urlService = urlService;
	}
	@Test
	void testCreateMapping() {
		urlService.createUrl("gmu", "georgemasonuniversity.edu");
		Assertions.assertTrue(urlMappingRepository.existsById("gmu"));
	}
	@Test
	void testDuplicateKey() {
		urlService.createUrl("duplicate", "www.east.com");
		Assertions.assertThrows(DuplicateKeyException.class, () -> urlService.createUrl("duplicate", "west.com"));
	}
	@Test
	void testDeleteMapping() {
		urlService.createUrl("map", "mapping.com");
		urlMappingRepository.deleteById("map");
		Assertions.assertFalse(urlMappingRepository.existsById("map"));
	}
	@Test
	void testGetUrl() {
		urlService.createUrl("key", "value");
		Assertions.assertEquals(urlService.getUrlByKey("key"), "value");
	}
	@Test
	void testGetInvalidUrl() {
		Assertions.assertThrows(NullPointerException.class, () -> urlService.getUrlByKey("invalidKey"));
	}
	@AfterAll
	void teardown() {
		urlMappingRepository.deleteAll();
	}
}