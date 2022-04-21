package com.sprint2.webapi.controllers;

import com.sprint2.webapi.models.Auction;
import com.sprint2.webapi.payload.request.AuctionRequest;
import com.sprint2.webapi.repository.AuctionRepository;
import com.sprint2.webapi.security.services.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class AuctionController {


    @Autowired
    AuctionRepository auctionRepository;
    @Autowired
    AuctionService auctionService;


    @PostMapping("/createauction")
    public ResponseEntity<?> createAuction(@Valid @RequestBody AuctionRequest auctionRequest) {
        Auction auction = new Auction(auctionRequest.getOwnerId(),
                auctionRequest.getImageURL(),auctionRequest.getCategory(),
                auctionRequest.getName(),auctionRequest.getDescription(),auctionRequest.getPrice(),
                auctionRequest.getBuyout(),auctionRequest.getEndTime(),auctionRequest.getOrderStatus());


        auctionRepository.save(auction);

        return ResponseEntity.ok("User registered successfully!");
    }


    @GetMapping("/getById/{id}")
    public Auction getAuctionById(@PathVariable String id){
        return auctionService.getAuctionById(id);
    }

    @GetMapping("/auctions")
    public List<Auction> getAllAuctions() {
        return auctionRepository.findAll();
    }
}

