package com.sprint2.webapi.services;

import com.sprint2.webapi.models.Auction;
import com.sprint2.webapi.models.Bid;
import com.sprint2.webapi.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuctionService {

    @Autowired
    AuctionRepository auctionRepository;

    public Auction createAuction(Auction auction) {
        return auctionRepository.save(auction);
    }
}
