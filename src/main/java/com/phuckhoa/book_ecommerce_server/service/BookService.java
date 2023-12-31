package com.phuckhoa.book_ecommerce_server.service;

import java.util.List;

import com.phuckhoa.book_ecommerce_server.DTO.BookResultDTO;
import com.phuckhoa.book_ecommerce_server.model.Book;

public interface BookService {
    void createBook(Book book);

    Book getBook(String title);

    List<Book> getBooks();

    List<Book> getBooksDependOnAuthor(String author);

    List<Book> getBooksDependOnCategory(String bookCategory);

    BookResultDTO createNewBook(Book book, List<Long> bookCategories);

    String updateBookQuantity(String quantity, String bookid);

}
