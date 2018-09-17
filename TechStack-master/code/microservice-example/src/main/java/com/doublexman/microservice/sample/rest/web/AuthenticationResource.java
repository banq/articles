package com.doublexman.microservice.sample.rest.web;


import com.doublexman.common.auth.UserAuthProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@RestController
@RequestMapping("/web-api/auth")
public class AuthenticationResource {

    private final UserAuthProvider userAuthProvider;

    public AuthenticationResource(UserAuthProvider userAuthProvider) {
        this.userAuthProvider = userAuthProvider;
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> login(HttpServletRequest request) {
        String userName = request.getParameter("user_name");
        String password = request.getParameter("password");
        if ("peter".equals(userName) && "peter".equals(password)) {
            String token = userAuthProvider.createUserAuthToken(userName, Arrays.asList("USER"));
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
