package com.sprint2.webapi.security.services;

import com.sprint2.webapi.models.User;
import com.sprint2.webapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        /*
        User user = new User(
                userRepository.findById(id).get().getFirstName(),
                userRepository.findById(id).get().getLastName(),
                userRepository.findById(id).get().getUsername(),
                userRepository.findById(id).get().getEmail(),
                userRepository.findById(id).get().getStreetAddress(),
                userRepository.findById(id).get().getPostCode(),
                userRepository.findById(id).get().getCity()
        );
         */

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

    UserDetailsImpl currentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userDetails;

    }

    public User get() {
        return userRepository.findById(currentUser().getId()).get();
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
            urs.setEmail(user.getEmail());
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

    public void deleteUser (String id){
        userRepository.deleteById(id);
    }
}


    
