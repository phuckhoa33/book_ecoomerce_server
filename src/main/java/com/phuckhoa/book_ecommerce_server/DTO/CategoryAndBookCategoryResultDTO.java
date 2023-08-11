package com.phuckhoa.book_ecommerce_server.DTO;

import java.util.List;

import com.phuckhoa.book_ecommerce_server.model.BookCategory;
import com.phuckhoa.book_ecommerce_server.model.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryAndBookCategoryResultDTO {
    private List<Category> categories;
    private List<BookCategory> bookCategories;
}
