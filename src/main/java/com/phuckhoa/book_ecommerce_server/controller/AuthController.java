package com.phuckhoa.book_ecommerce_server.controller;

import java.nio.CharBuffer;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phuckhoa.book_ecommerce_server.DTO.AuthenticationDTO;
import com.phuckhoa.book_ecommerce_server.DTO.EmailInputDataDTO;
import com.phuckhoa.book_ecommerce_server.config.JwtService;
import com.phuckhoa.book_ecommerce_server.model.User;
import com.phuckhoa.book_ecommerce_server.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody User user) {
        HashMap<String, Object> result = new HashMap<>();
        try {

            AuthenticationDTO data = userService.register(user);
            result.put("success", true);
            result.put("message", "Success to call API register");
            result.put("data", data);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Fail to call API register");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody User user) {
        HashMap<String, Object> result = new HashMap<>();
        try {

            AuthenticationDTO data = userService.login(user);
            result.put("success", true);
            result.put("message", "Success to call API GetAllUsers");
            result.put("data", data);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Fail to call API GetAllUsers");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("forgot-password")
    ResponseEntity<?> sendEmail(@RequestBody EmailInputDataDTO request) {
        HashMap<String, Object> result = new HashMap<>();
        try {

            String data = userService.sendEmail(request);
            result.put("success", true);
            result.put("message", "Success to call API Send Email for reset password");
            result.put("data", data);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Fail to call API Send Email for reset password");
            result.put("data", null);
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/reset-password")
    ResponseEntity<?> resetPassword(@RequestBody User user) {
        HashMap<String, Object> result = new HashMap<>();
        try {
            String hashedNewPassword = passwordEncoder.encode(CharBuffer.wrap(user.getPassword()));
            user.setPassword(hashedNewPassword);
            AuthenticationDTO data = userService.updateUser(user);
            result.put("success", true);
            result.put("message", "Success to call API reset password");
            result.put("data", data);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Fail to call API reset password");
            result.put("data", null);
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

}
