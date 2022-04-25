package com.sprint2.webapi.services;

import com.sprint2.webapi.models.Bid;
import com.sprint2.webapi.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Bid> getAllBids() {
        return bidRepository.findAll();
    }

    public Bid getHighestBid(String id){
        List<Bid> bidList = bidRepository.findByAuctionId(id);
        double currentBid = 0;
        Bid highestBid = null;
        for (Bid bid: bidList) {
            if (bid.getBidAmount() >= currentBid) {
                currentBid = bid.getBidAmount();
                highestBid = bid;
            }
        }
        return highestBid;
    }

    public Bid getMyHighestBid(String auctionId, String userId) {
        List<Bid> bidList = bidRepository.findByAuctionIdAndUserId(auctionId, userId);
        double currentBid = 0;
        Bid myHighestBid = null;
        for (Bid bid: bidList) {
            if (bid.getBidAmount() >= currentBid) {
                currentBid = bid.getBidAmount();
                myHighestBid = bid;
            }
        }
        return myHighestBid;
    }
}
