package com.phuckhoa.book_ecommerce_server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phuckhoa.book_ecommerce_server.mapper.DiscountMapper;
import com.phuckhoa.book_ecommerce_server.model.Discount;
import com.phuckhoa.book_ecommerce_server.service.DiscountService;
import com.phuckhoa.book_ecommerce_server.service.ExtraService;

@Service
public class DiscountServicelmpl implements DiscountService {

    @Autowired
    DiscountMapper discountMapper;

    @Autowired
    ExtraService extraService;

    @Override
    public List<Discount> getAllDiscounts() {
        return discountMapper.getAllDiscounts();
    }

    @Override
    public Discount getDiscount(String discountid) {
        return discountMapper.getDiscount(discountid);
    }

    @Override
    public void createDiscount(Discount discount) {
        discountMapper.createDiscount(discount);
    }

    @Override
    public String createNewDiscount(Discount discount) {
        Discount oldDiscount = discountMapper.getDiscount(discount.getTitle());
        if (oldDiscount != null) {
            return "This discount label is exist";
        }
        Long discountid = extraService.createRandomId(10);

        discount.setId(discountid);
        discountMapper.createDiscount(discount);
        return "Add discount is successfully";
    }

}
