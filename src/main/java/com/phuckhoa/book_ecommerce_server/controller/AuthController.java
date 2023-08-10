package com.phuckhoa.book_ecommerce_server.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phuckhoa.book_ecommerce_server.DTO.AuthenticationDTO;
import com.phuckhoa.book_ecommerce_server.DTO.EmailInputDataDTO;
import com.phuckhoa.book_ecommerce_server.model.User;
import com.phuckhoa.book_ecommerce_server.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody User user) {
        HashMap<String, Object> result = new HashMap<>();
        try {

            String message = userService.register(user);
            AuthenticationDTO data = new AuthenticationDTO(user, message);
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

            String message = userService.login(user);
            AuthenticationDTO data = new AuthenticationDTO(user, message);
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

    @PostMapping("forgotPassword")
    ResponseEntity<?> sendEmail(@RequestBody EmailInputDataDTO request) {
        HashMap<String, Object> result = new HashMap<>();
        try {

            String data = userService.sendEmail(request);
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

    @PutMapping()
    ResponseEntity<?> resetPassword(@RequestBody User user) {
        HashMap<String, Object> result = new HashMap<>();
        try {

            String data = userService.updateUser(user);
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

}
