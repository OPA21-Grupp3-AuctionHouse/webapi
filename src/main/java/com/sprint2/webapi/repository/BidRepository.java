package com.sprint2.webapi.repository;

import com.sprint2.webapi.models.Bid;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BidRepository extends MongoRepository<Bid, String> {
}
