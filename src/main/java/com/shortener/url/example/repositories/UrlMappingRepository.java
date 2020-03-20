package com.shortener.url.example.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.shortener.url.example.model.UrlMapping;

public interface UrlMappingRepository extends MongoRepository<UrlMapping, String> {
    
}