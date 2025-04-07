package com.example.api.controller;
import com.example.api.JwtUtil;
import com.example.api.PasswordUtils;
import com.example.api.model.User;
import com.example.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) throws Exception {
        User user = userService.findByEmail(loginRequest.getEmail());
        if(user != null) {
            boolean b = PasswordUtils.verifyPassword(loginRequest.getPassword(), user.getSalt(), user.getPassword());
            if(!b) {
                return ResponseEntity.status(401).body("Invalid username or password");
            }
        }
        else if ( user == null ) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        String token = JwtUtil.generateToken(user.getEmail(), String.valueOf(user.getId()));
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }
}
