package com.sprint2.webapi.repository;

import com.sprint2.webapi.models.Auction;
import com.sprint2.webapi.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AuctionRepository extends MongoRepository<Auction, String> {
    Optional<Auction> findById(String s);
}
