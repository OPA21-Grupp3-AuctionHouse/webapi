package com.sprint2.webapi.controllers;

import com.sprint2.webapi.models.Auction;
import com.sprint2.webapi.repository.AuctionRepository;
import com.sprint2.webapi.services.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AuctionController {

    @Autowired
    AuctionRepository auctionRepository;
    @Autowired
    AuctionService auctionService;


    @PostMapping("/createauction")
    public ResponseEntity<?> createAuction(@Valid @RequestBody Auction auction) {


        auctionService.createAuction(auction);

        return ResponseEntity.ok("User registered successfully!");

    }

    @GetMapping("/auctions")
    public List<Auction> getAllAuctions() {
        return auctionService.getAuction();
    }

    @GetMapping("/getById/{id}")
    public Auction getAuctionById(@PathVariable String id){
        return auctionService.getAuctionById(id);

    }


}

