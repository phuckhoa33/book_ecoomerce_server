package com.phuckhoa.book_ecommerce_server.service;

import java.util.List;

import com.phuckhoa.book_ecommerce_server.model.Category;

public interface CategoryService {
    String createCategory(Category category);

    Category getCategory(String name);

    List<Category> getCategories();
}
