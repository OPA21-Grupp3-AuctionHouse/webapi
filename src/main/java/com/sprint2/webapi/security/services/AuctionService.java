package com.sprint2.webapi.security.services;


import com.sprint2.webapi.models.Auction;
import com.sprint2.webapi.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuctionService {
    @Autowired
    AuctionRepository auctionRepository;

    public Auction getAuctionById(String id){
        return auctionRepository.findById(id).get();
    }
}
