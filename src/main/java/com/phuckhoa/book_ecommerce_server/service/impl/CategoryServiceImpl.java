package com.phuckhoa.book_ecommerce_server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phuckhoa.book_ecommerce_server.mapper.CategoryMapper;
import com.phuckhoa.book_ecommerce_server.model.Category;
import com.phuckhoa.book_ecommerce_server.service.CategoryService;
import com.phuckhoa.book_ecommerce_server.service.ExtraService;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    ExtraService extraService;

    @Override
    public String createCategory(Category category) {
        Category oldCategory = categoryMapper.getCategory(category.getName());
        if (oldCategory != null && (oldCategory.getName() == category.getName())) {
            return "Category is exist";
        }
        category.setId(extraService.createRandomId(10));
        categoryMapper.createCategory(category);
        return "Create category is successfully";
    }

    @Override
    public Category getCategory(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCategory'");
    }

    @Override
    public List<Category> getCategories() {
        return categoryMapper.getCategories();
    }
}
