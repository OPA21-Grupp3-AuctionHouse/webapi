package com.sprint2.webapi.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TestController has accessing protected resource methods with authentication based validations.


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class TestController {
    @GetMapping("/welcomepage")
    public String allAccess() {
        return "Welcome Page";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/startpage")
    public String userAccess() {
        return "User Content, Card Bazaar.";
    }
}
