package com.sprint2.webapi.controllers;

import com.sprint2.webapi.models.Bid;
import com.sprint2.webapi.security.services.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class BidController {

    @Autowired
    BidService bidService;

    @PostMapping("/create")
    public Bid createBid(@RequestBody Bid bid) {
        return bidService.createBid(bid);
    }

    @GetMapping("/getById/{id}")
    public Bid geBid(@PathVariable String id) {
        return bidService.getBid(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBid(@PathVariable String id) {
        return bidService.deleteBid(id);
    }
}
