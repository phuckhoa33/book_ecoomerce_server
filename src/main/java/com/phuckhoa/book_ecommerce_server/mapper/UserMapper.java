package com.phuckhoa.book_ecommerce_server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.phuckhoa.book_ecommerce_server.model.User;

@Mapper
public interface UserMapper {
    List<User> getAllUsers();

    void createNewUser(User user);

    User getUserByLastName(@Param("username") String username);

    User checkEmailExist(@Param("email") String email);

    User getUserByEmail(@Param("email") String email);

    User getUser(@Param("userid") Long userid);

    void updateUser(User user);
}
