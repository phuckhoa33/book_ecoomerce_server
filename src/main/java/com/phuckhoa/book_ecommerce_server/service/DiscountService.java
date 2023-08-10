package com.phuckhoa.book_ecommerce_server.service;

import java.util.List;

import com.phuckhoa.book_ecommerce_server.model.Discount;

public interface DiscountService {
    List<Discount> getAllDiscounts();

    Discount getDiscount(String discountid);

    void createDiscount(Discount discount);

    String createNewDiscount(Discount discount);
}
