package com.phuckhoa.book_ecommerce_server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.phuckhoa.book_ecommerce_server.model.Category;

@Mapper
public interface CategoryMapper {
    void createCategory(Category category);

    Category getCategory(@Param("name") String name);

    List<Category> getCategories();
}
