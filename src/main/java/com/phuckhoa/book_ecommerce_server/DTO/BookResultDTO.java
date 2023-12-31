package com.phuckhoa.book_ecommerce_server.DTO;

import com.phuckhoa.book_ecommerce_server.model.Book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookResultDTO extends ResultDTO {

    private Book book;

    public BookResultDTO(Book book, String message) {
        this.book = book;
        this.setMessage(message);
    }
}
