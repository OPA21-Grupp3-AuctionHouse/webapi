package com.sprint2.webapi.controllers;

import com.sprint2.webapi.models.Auction;
import com.sprint2.webapi.repository.AuctionRepository;
import com.sprint2.webapi.services.AuctionService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class AuctionController {

    private final AuctionService auctionService;

    @Autowired
    AuctionRepository auctionRepository;


    @PostMapping("/createauction")
    public ResponseEntity<?> createAuction(@Valid @RequestBody Auction auction) {


        auctionRepository.save(auction);

        return ResponseEntity.ok("User registered successfully!");
    }


    @GetMapping("/auctions")
    public List<Auction> fetchAllAuctions() {
        return auctionService.getAllAuctions();
    }
}