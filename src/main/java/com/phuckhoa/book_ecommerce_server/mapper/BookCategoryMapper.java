package com.phuckhoa.book_ecommerce_server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.phuckhoa.book_ecommerce_server.model.BookCategory;

@Mapper
public interface BookCategoryMapper {
    void createBookCategory(BookCategory bookCategory);

    BookCategory getBookCategory(@Param("id") long id);

    List<BookCategory> getBookCategories();
}
