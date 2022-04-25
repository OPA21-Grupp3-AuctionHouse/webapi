package com.sprint2.webapi.security.services;

import com.sprint2.webapi.models.User;
import com.sprint2.webapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUserById(String id){
        return userRepository.findById(id).get();
    }

    public User getUserByUsername(String username){
        List<User> users = userRepository.findAll();
        for (User user: users) {
            if(Objects.equals(user.getUsername(), username)){
                return user;
            }
        }
        return null;
    }

    public User getUserByEmail(String email){
        List<User> users = userRepository.findAll();
        for (User user: users) {
            if(Objects.equals(user.getEmail(), email)){
                return user;
            }
        }
        return null;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    //update

    public User updateUserById(User user, String id){

        Optional<User> userData = userRepository.findById(id);

        if(userData.isPresent())
        {
            User urs = userData.get();
            urs.setFirstName(user.getFirstName());
            urs.setLastName(user.getLastName());
            urs.setStreetAddress(user.getStreetAddress());
            urs.setCity(user.getCity());
            urs.setPostCode(user.getPostCode());
            return userRepository.save(urs);
        }
        else
        {
            return null;
        }
        }
    }