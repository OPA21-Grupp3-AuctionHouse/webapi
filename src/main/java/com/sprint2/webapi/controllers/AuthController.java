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

import java.util.Locale;

//Controller receives and handles request after it was filtered by OncePerRequestFilter.
//AuthController handles signup/login requests

@CrossOrigin(origins = {"http://146.190.18.26:3000", "http://localhost:3000", "http://localhost:8080"}, maxAge = 3600, allowCredentials = "true")
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

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signUpRequest.getUsername(), signUpRequest.getPassword()));

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

    @RequestMapping(value="/logout", method= RequestMethod.GET)
    public ResponseEntity<?> logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        new SecurityContextLogoutHandler().logout(request, response, auth);


        ResponseCookie jwtCookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body("logged out" );


    }

    @PostMapping("/updatePassword")
    public ResponseEntity<?> changeUserPassword(@RequestParam("oldpassword") String oldPassword,
                                                @RequestParam("password") String password
                                                ) {
        User user = userService.get();

        if (!userService.checkIfValidOldPassword(user, oldPassword)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Wrong current password!"));

        }
        userService.changeUserPassword(user, password);
        return ResponseEntity
                .ok()
                .body(new MessageResponse("Password updated"));
    }


}
