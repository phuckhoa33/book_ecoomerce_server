package com.phuckhoa.book_ecommerce_server.DTO;

import com.phuckhoa.book_ecommerce_server.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationDTO extends ResultDTO {
    User user;

    public AuthenticationDTO(User user, String message) {
        this.user = user;
        this.setMessage(message);
    }
}
