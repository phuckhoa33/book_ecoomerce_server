package com.phuckhoa.book_ecommerce_server.service;

import java.util.List;

import com.phuckhoa.book_ecommerce_server.DTO.AuthenticationDTO;
import com.phuckhoa.book_ecommerce_server.DTO.EmailInputDataDTO;
import com.phuckhoa.book_ecommerce_server.model.User;

public interface UserService {
    List<User> getAllUsers();

    void createNewUser(User user);

    AuthenticationDTO register(User user);

    AuthenticationDTO login(User user);

    String sendEmail(EmailInputDataDTO request);

    AuthenticationDTO updateUser(User user);

    User getUserById(String userid);

    User getUserByEmail(String email);
}
