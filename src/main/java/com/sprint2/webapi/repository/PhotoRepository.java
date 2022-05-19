package com.sprint2.webapi.repository;

import com.sprint2.webapi.models.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepository extends MongoRepository<Photo, String> { }
