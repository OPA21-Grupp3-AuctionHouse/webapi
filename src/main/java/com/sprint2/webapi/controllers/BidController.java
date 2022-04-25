package com.sprint2.webapi.controllers;

import com.sprint2.webapi.models.Bid;
import com.sprint2.webapi.services.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class BidController {

    @Autowired
    BidService bidService;

    @PostMapping("/create")
    public Bid createBid(@RequestBody Bid bid) {
        return bidService.createBid(bid);
    }

    @GetMapping("/getById/{id}")
    public Bid getBid(@PathVariable String id) {
        return bidService.getBid(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBid(@PathVariable String id) {
        return bidService.deleteBid(id);
    }

    @GetMapping("/all")
    public List<Bid> getAllBids() {
        return bidService.getAllBids();
    }

    @GetMapping("/highestBid/{id}")
    public Bid getHighestBid(@PathVariable String id) {
        return bidService.getHighestBid(id);
    }
}
