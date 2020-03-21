package com.shortener.url.example;

import org.junit.jupiter.api.*;

import org.junit.jupiter.api.extension.ExtendWith;
import com.shortener.url.example.model.UrlMapping;
import com.shortener.url.example.repositories.UrlMappingRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles(profiles = {"test"})
class UrlShortenerTests {
	@Autowired
	private UrlMappingRepository urlMappingRepository;
	
	@BeforeAll
	public void setup() throws Exception {
		List<UrlMapping> urlMappings = new ArrayList<UrlMapping>();
		urlMappings.add(new UrlMapping("jsu", "jacksonstateuniversity.edu"));
		urlMappings.add(new UrlMapping("umd", "universityofmaryland.edu"));

		urlMappingRepository.insert(urlMappings);
	}
	@Test
	void testCreateMapping() {
		createUrlMapping("gmu", "georgemasonuniversity.edu");
		Assertions.assertTrue(urlMappingRepository.existsById("gmu"));
	}
	@Test
	void testDuplicateKey() {
		createUrlMapping("duplicate", "www.east.com");
		Assertions.assertThrows(DuplicateKeyException.class, () -> createUrlMapping("duplicate", "west.com"));
	}
	@Test
	void testDeleteMapping() {
		createUrlMapping("map", "mapping.com");
		urlMappingRepository.deleteById("map");
		Assertions.assertFalse(urlMappingRepository.existsById("map"));
	}
	private void createUrlMapping(String key, String value){
		urlMappingRepository.insert(new UrlMapping(key, value));
	}
	@AfterEach
	void teardown(){
		urlMappingRepository.deleteAll();
	}
}