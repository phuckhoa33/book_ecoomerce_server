package com.phuckhoa.book_ecommerce_server.DTO;

import java.util.List;

import com.phuckhoa.book_ecommerce_server.model.Book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookInputData {
    private Book book;
    private List<Long> bookCategories;
}
