package com.sprint2.webapi.services;

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

    public List<Auction> getAllAuctions() {
        return auctionRepository.findAll();
    }
}
