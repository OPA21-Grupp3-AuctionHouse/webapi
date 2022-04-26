package com.sprint2.webapi.repository;

import com.sprint2.webapi.models.Auction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuctionRepository extends MongoRepository<Auction, String> {

}
