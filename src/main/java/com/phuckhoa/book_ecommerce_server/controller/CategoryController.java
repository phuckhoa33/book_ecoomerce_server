package com.phuckhoa.book_ecommerce_server.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phuckhoa.book_ecommerce_server.DTO.CategoryAndBookCategoryResultDTO;
import com.phuckhoa.book_ecommerce_server.DTO.CategoryResultDTO;
import com.phuckhoa.book_ecommerce_server.model.Category;
import com.phuckhoa.book_ecommerce_server.service.CategoryService;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping()
    ResponseEntity<?> createCategory(@RequestBody Category category) {
        HashMap<String, Object> result = new HashMap<>();
        try {

            String message = categoryService.createCategory(category);
            CategoryResultDTO data = new CategoryResultDTO(category, message);
            result.put("success", true);
            result.put("message", "Success to call API create book");
            result.put("data", data);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Fail to call API create book");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping()
    ResponseEntity<?> getCategories() {
        HashMap<String, Object> result = new HashMap<>();
        try {

            CategoryAndBookCategoryResultDTO data = categoryService.getCategories();
            result.put("success", true);
            result.put("message", "Success to call API create book");
            result.put("data", data);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Fail to call API create book");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }
}
