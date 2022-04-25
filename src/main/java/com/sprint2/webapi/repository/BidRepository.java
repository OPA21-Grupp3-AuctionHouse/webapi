package com.sprint2.webapi.repository;

import com.sprint2.webapi.models.Bid;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BidRepository extends MongoRepository<Bid, String> {
    Bid findByBidAmount(Bid bid);
    List<Bid> findByAuctionId(String id);
}
