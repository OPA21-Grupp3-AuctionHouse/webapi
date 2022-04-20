package com.sprint2.webapi.security.services;

import com.sprint2.webapi.models.Bid;
import com.sprint2.webapi.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidService {

    @Autowired
    BidRepository bidRepository;

    public Bid createBid(Bid bid) {
        return bidRepository.save(bid);
    }

    public Bid getBid(String id) {
        return bidRepository.findById(id).get();
    }

    public String deleteBid(String id) {
        bidRepository.deleteById(id);
        return "bid deleted";
    }
}
