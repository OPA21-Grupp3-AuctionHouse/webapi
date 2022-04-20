package com.sprint2.webapi.controllers;

import com.sprint2.webapi.models.Auction;
import com.sprint2.webapi.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class AuctionController {


    @Autowired
    AuctionRepository auctionRepository;


    @PostMapping("/createauction")
    public ResponseEntity<?> createAuction(@Valid @RequestBody Auction auction) {


        auctionRepository.save(auction);

        return ResponseEntity.ok("User registered successfully!");
    }


    @GetMapping("/auctions")
    public List<Auction> getAllAuctions() {
        return auctionRepository.findAll();
    }
}

