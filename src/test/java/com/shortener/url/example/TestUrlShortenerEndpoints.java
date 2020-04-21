package com.shortener.url.example;

import com.shortener.url.example.repositories.UrlMappingRepository;
import com.shortener.url.example.services.UrlService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles(profiles = {"test"})
class TestUrlShortenerEndpoints {

    @LocalServerPort
    private int port;

    private UrlService urlService;
    private UrlMappingRepository urlMappingRepository;

    @Autowired
    TestUrlShortenerEndpoints(UrlService urlService, UrlMappingRepository urlMappingRepository) {
        this.urlService = urlService;
        this.urlMappingRepository = urlMappingRepository;
    }

    @BeforeAll
    public void setup() throws AssertionError{

        urlService.createUrl("jsu", "jacksonstateuniversity.edu");
        urlService.createUrl("umd", "universityofmaryland.edu");
        Assertions.assertAll(
                () -> Assertions.assertTrue(urlMappingRepository.existsById("jsu")),
                () -> Assertions.assertTrue(urlMappingRepository.existsById("umd"))
        );
    }
    /*@Test
    public void testRouteUrl() throws IOException {

        String url = "http://localhost:" + port + "/jsu";
        HttpURLConnection connection = (HttpURLConnection) (new URL(url)).openConnection();
        connection.setRequestMethod(RequestMethod.GET.toString());
        Assertions.assertEquals(HttpStatus.OK.value(), connection.getResponseCode());

    }*/
    @Test
    public void testCreateUrl() throws IOException {

        String json = "{ \"url\" : \"www.test.edu\" , \"key\" : \"test\" }";
        String url = "http://localhost:" + port + "/shorten";
        HttpURLConnection connection = (HttpURLConnection) (new URL(url)).openConnection();
        connection.setRequestMethod(RequestMethod.POST.toString());
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(json.getBytes());
        outputStream.flush();

        Assertions.assertEquals(HttpStatus.OK.value(), connection.getResponseCode());
        outputStream.close();
    }
    @AfterAll
    public void teardown() {
        urlMappingRepository.deleteAll();
    }
}
