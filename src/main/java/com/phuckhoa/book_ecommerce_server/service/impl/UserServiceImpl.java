package com.phuckhoa.book_ecommerce_server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phuckhoa.book_ecommerce_server.DTO.Email;
import com.phuckhoa.book_ecommerce_server.mapper.UserMapper;
import com.phuckhoa.book_ecommerce_server.model.User;
import com.phuckhoa.book_ecommerce_server.service.ExtraService;
import com.phuckhoa.book_ecommerce_server.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    ExtraService extraService;

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public void createNewUser(User user) {
        userMapper.createNewUser(user);
    }

    @Override
    public String register(User user) {
        Email email = userMapper.checkEmailExist(user.getEmail());
        if (email != null) {
            return "Account is exist";
        }
        user.setId(extraService.createRandomId(10));
        this.createNewUser(user);
        return "Register is successfully";
    }

    @Override
    public String login(User user) {
        Email email = userMapper.checkEmailExist(user.getEmail());
        if (email == null) {
            return "Account is not exist";
        }
        List<User> users = this.getAllUsers();
        if (users.get(0).getPassword() != user.getPassword()) {
            return "Password is not match";
        }
        return "Login is successfuly";
    }

}
