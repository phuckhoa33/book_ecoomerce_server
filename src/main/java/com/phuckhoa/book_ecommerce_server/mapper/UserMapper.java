package com.phuckhoa.book_ecommerce_server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.phuckhoa.book_ecommerce_server.DTO.Email;
import com.phuckhoa.book_ecommerce_server.model.User;

@Mapper
public interface UserMapper {
    List<User> getAllUsers();

    void createNewUser(User user);

    Email checkEmailExist(@Param("email") String email);
}
