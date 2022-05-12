package com.sprint2.webapi.controllers;

import com.sprint2.webapi.models.User;
import com.sprint2.webapi.security.services.UserService;
import com.sprint2.webapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/getById/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping("/getByUsername/{username}")
    public User getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @GetMapping("/getByEmail/{email}")
    public User getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    //Update User information
    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user){
        return userService.updateUserById(user, id);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteUser (@PathVariable String id) {
        userService.deleteUser(id);
    }

}
