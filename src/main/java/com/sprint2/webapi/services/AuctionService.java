package com.sprint2.webapi.services;



import com.sprint2.webapi.models.Auction;
import com.sprint2.webapi.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


import java.util.List;
import com.sprint2.webapi.repository.AuctionRepository;
import com.sprint2.webapi.models.Auction;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuctionService {


    @Autowired
    AuctionRepository auctionRepository;

    public Auction createAuction(Auction auction) {
        return auctionRepository.save(auction);

    }

    public List<Auction> getAuction() {
        return auctionRepository.findAll();

    }

    public Auction getAuctionById(String id) {
        return auctionRepository.findById(id).get();

    }


}