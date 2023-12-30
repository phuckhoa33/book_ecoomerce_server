package com.phuckhoa.book_ecommerce_server.service.impl;

import java.nio.CharBuffer;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.phuckhoa.book_ecommerce_server.DTO.AuthenticationDTO;
import com.phuckhoa.book_ecommerce_server.DTO.EmailDetailsDTO;
import com.phuckhoa.book_ecommerce_server.DTO.EmailInputDataDTO;
import com.phuckhoa.book_ecommerce_server.DTO.EmailVariable;
import com.phuckhoa.book_ecommerce_server.DTO.InputEmailData;
import com.phuckhoa.book_ecommerce_server.config.JwtService;
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
    JwtService jwtService;

    @Autowired
    EmailService emailService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${frontend.url}")
    private String frontendPath;

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public void createNewUser(User user) {
        userMapper.createNewUser(user);
    }

    @Override
    public AuthenticationDTO register(User user) {
        User oldUser = userMapper.checkEmailExist(user.getEmail());
        if (oldUser != null) {
            return AuthenticationDTO.builder()
                    .message("Account is exist")
                    .token(null)
                    .build();
        }
        user.setId(extraService.createRandomId(10));
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(user.getPassword())));
        this.createNewUser(user);

        String token = jwtService.generateToken(user);

        return AuthenticationDTO.builder()
                .message("Register is successfully")
                .token(token)
                .build();
    }

    @Override
    public AuthenticationDTO login(User user) {
        User oldUser = userMapper.checkEmailExist(user.getEmail());
        if (oldUser == null) {
            return AuthenticationDTO.builder()
                    .message("Account is not exist")
                    .token(null)
                    .build();
        }

        if (!passwordEncoder.matches(CharBuffer.wrap(user.getPassword()), oldUser.getPassword())) {

            return AuthenticationDTO.builder()
                    .message("Password is not match")
                    .token(null)
                    .build();
        }

        String token = jwtService.generateToken(user);
        return AuthenticationDTO.builder()
                .message("Login is successfuly")
                .token(token)
                .build();
    }

    @Override
    public String sendEmail(EmailInputDataDTO data) {
        String message = "Send Email is successfully";
        User user = userMapper.getUserByEmail(data.getEmail());
        try {
            // Create token
            String token = jwtService.generateToken(user);

            // End path
            String endPath = frontendPath + "reset-password/" + token;

            InputEmailData emailData = new InputEmailData();
            EmailDetailsDTO details = new EmailDetailsDTO();
            details.setRecipient(data.getEmail());
            details.setSubject(message);
            EmailVariable variables = EmailVariable.builder()
                    .path(endPath)
                    .build();
            details.setVariables(variables);
            emailData.setDetail(details);

            emailService.sendSimpleMail(emailData, "resetPasswordLetter");
        } catch (Exception e) {
            e.printStackTrace();
            message = "Send email is failed";
        }

        return message;
    }

    @Override
    public AuthenticationDTO updateUser(User user) {
        String message = "Update user is successfully";
        String token = null;
        try {
            User findedUser = userMapper.checkEmailExist(user.getEmail());
            if (findedUser == null) {
                return AuthenticationDTO.builder().message("This user is not exist").token(token).build();
            }
            user.setId(findedUser.getId());
            userMapper.updateUser(user);
            token = jwtService.generateToken(user);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            message = "Update user is failed";
        }

        return AuthenticationDTO.builder().message(message).token(token).build();
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
