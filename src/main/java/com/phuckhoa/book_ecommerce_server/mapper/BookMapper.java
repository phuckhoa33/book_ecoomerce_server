package com.phuckhoa.book_ecommerce_server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.phuckhoa.book_ecommerce_server.model.*;

@Mapper
public interface BookMapper {
    List<Book> getBooks();

    List<Book> getBooksDependOnCategory(@Param("categoryid") String categoryid);

    List<Book> getBooksDependOnAuthor(@Param("author") String author);

    Book getBook(@Param("title") String title);

    Book getBookDependById(@Param("id") Long id);

    void createBook(Book book);

    void updateBookQuantity(@Param("quantity") String quantity, @Param("bookid") String bookid);
}
