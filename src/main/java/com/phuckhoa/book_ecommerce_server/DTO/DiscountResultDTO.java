package com.phuckhoa.book_ecommerce_server.DTO;

import com.phuckhoa.book_ecommerce_server.model.Discount;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountResultDTO extends ResultDTO {
    private Discount discount;

    public DiscountResultDTO(Discount discount, String message) {
        this.discount = discount;
        this.setMessage(message);
    }
}
