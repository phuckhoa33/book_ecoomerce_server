package com.phuckhoa.book_ecommerce_server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.phuckhoa.book_ecommerce_server.DTO.EmailDetailsDTO;
import com.phuckhoa.book_ecommerce_server.DTO.EmailInputDataDTO;
import com.phuckhoa.book_ecommerce_server.DTO.PaymentEmailDetailsDTO;
import com.phuckhoa.book_ecommerce_server.mapper.UserMapper;
import com.phuckhoa.book_ecommerce_server.model.User;
import com.phuckhoa.book_ecommerce_server.service.EmailService;
import com.phuckhoa.book_ecommerce_server.service.ExtraService;
import com.phuckhoa.book_ecommerce_server.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    ExtraService extraService;

    @Autowired
    EmailService emailService;

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
        User oldUser = userMapper.checkEmailExist(user.getEmail());
        if (oldUser != null) {
            return "Account is exist";
        }
        user.setId(extraService.createRandomId(10));
        this.createNewUser(user);
        return "Register is successfully";
    }

    @Override
    public String login(User user) {
        User oldUser = userMapper.checkEmailExist(user.getEmail());
        if (oldUser == null) {
            return "Account is not exist";
        }

        System.out.println("Olduser" + oldUser.getPassword());

        if (!oldUser.getPassword().matches(user.getPassword())) {
            return "Password is not match";
        }
        return "Login is successfuly";
    }

    @Override
    public String sendEmail(EmailInputDataDTO request) {
        String message = "Send Email is successfully";
        try {
            PaymentEmailDetailsDTO payment = new PaymentEmailDetailsDTO();
            EmailDetailsDTO details = new EmailDetailsDTO();
            details.setRecipient(request.getEmail());
            details.setSubject(message);
            payment.setEmailDetails(details);
            emailService.sendSimpleMail(payment, "resetPasswordLetter");
        } catch (Exception e) {
            message = "Send email is failed";
        }

        return message;
    }

    @Override
    public String updateUser(User user) {
        String message = "Update user is successfully";
        try {
            userMapper.updateUser(user);
        } catch (Exception e) {
            // TODO: handle exception
            message = "Update user is failed";
        }
        return message;
    }

    @Override
    public User getUserById(String userid) {
        return userMapper.getUser(Long.parseLong(userid));
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.checkEmailExist(email);
    }

}
