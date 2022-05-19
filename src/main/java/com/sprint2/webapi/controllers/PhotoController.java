package com.sprint2.webapi.controllers;


import com.sprint2.webapi.models.Photo;
import com.sprint2.webapi.services.AuctionService;
import com.sprint2.webapi.services.PhotoService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8080"}, maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/")
public class PhotoController {

    private final PhotoService photoService;

    @PostMapping("/photos/add")
    public String addPhoto(@RequestParam("image") MultipartFile image, Model model)
            throws IOException {
        String id = photoService.addPhoto(image);
        return id;
    }

    @GetMapping("/photos/{id}")
    public String getPhoto(@PathVariable String id, Model model) {
        Photo photo = photoService.getPhoto(id);
        model.addAttribute("image",
                Base64.getEncoder().encodeToString(photo.getImage().getData()));
        return "photos";
    }
}
