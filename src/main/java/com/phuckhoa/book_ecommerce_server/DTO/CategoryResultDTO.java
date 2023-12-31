package com.phuckhoa.book_ecommerce_server.DTO;

import com.phuckhoa.book_ecommerce_server.model.Category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResultDTO extends ResultDTO {
    private Category category;

    public CategoryResultDTO(Category category, String message) {
        this.category = category;
        this.setMessage(message);
    }
}
