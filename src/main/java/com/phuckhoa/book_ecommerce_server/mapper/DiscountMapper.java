package com.phuckhoa.book_ecommerce_server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.phuckhoa.book_ecommerce_server.model.Discount;

@Mapper
public interface DiscountMapper {
    List<Discount> getAllDiscounts();

    Discount getDiscount(@Param("title") String title);

    Discount getDiscountByDiscountid(@Param("discountid") String discountid);

    void createDiscount(Discount discount);

}
