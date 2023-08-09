package com.phuckhoa.book_ecommerce_server.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private Long id;
    private String password;
    private String avatar;
    private String email;
    private Long roleId;
    private String phone;
    private String birthday;
    private String firstname;
    private String lastname;
    private String address;
}