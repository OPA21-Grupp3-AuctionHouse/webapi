package com.sprint2.webapi.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.sprint2.webapi.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import com.sprint2.webapi.models.User;
import com.sprint2.webapi.payload.request.LoginRequest;
import com.sprint2.webapi.payload.request.SignupRequest;
import com.sprint2.webapi.payload.response.MessageResponse;
import com.sprint2.webapi.payload.response.UserInfoResponse;
import com.sprint2.webapi.repository.UserRepository;
import com.sprint2.webapi.security.jwt.JwtUtils;
import com.sprint2.webapi.security.services.UserDetailsImpl;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Optional;


//Controller receives and handles request after it was filtered by OncePerRequestFilter.
//AuthController handles signup/login requests

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;


    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail()
                        )
                );
    }

    @PostMapping("/forgotPassword/{username}")
    public String newPassword(@PathVariable String username) {
        if (userRepository.existsByUsername(username)) {
            String generatedString = RandomStringUtils.randomAlphanumeric(8);
            User user = userService.getUserByUsername(username);
            String email = user.getEmail();

            userService.changeUserPassword(user, generatedString);


            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("cardbazaar3@gmail.com");
            message.setTo(email);
            message.setSubject("New Password");
            message.setText("Here is your new password: "+generatedString+ "\nFor account with name: " +username);
            emailSender.send(message);
            return "email sent to "+email;
        }
        else {
            return "failed for "+username;
        }


    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getStreetAddress(),
                signUpRequest.getPostCode(),
                signUpRequest.getCity());

        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @RequestMapping(value="/logout", method= RequestMethod.GET)
    public ResponseEntity<?> logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            new SecurityContextLogoutHandler().logout(request, response, auth);


            ResponseCookie jwtCookie = jwtUtils.getCleanJwtCookie();
            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body("logged out" );


    }

}
