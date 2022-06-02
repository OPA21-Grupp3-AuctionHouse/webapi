package com.sprint2.webapi.security.services;

import com.sprint2.webapi.models.User;
import com.sprint2.webapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

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

    UserDetailsImpl currentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            return userDetails;

    }

    public void changeUserPassword(final User user, final String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
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

    
