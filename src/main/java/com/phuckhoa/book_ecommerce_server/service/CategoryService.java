package com.phuckhoa.book_ecommerce_server.service;

import com.phuckhoa.book_ecommerce_server.DTO.CategoryAndBookCategoryResultDTO;
import com.phuckhoa.book_ecommerce_server.model.Category;

public interface CategoryService {
    String createCategory(Category category);

    Category getCategory(String name);

    CategoryAndBookCategoryResultDTO getCategories();
}
