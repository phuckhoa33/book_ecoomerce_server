package com.phuckhoa.book_ecommerce_server.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.phuckhoa.book_ecommerce_server.model.BillDiscount;

@Mapper
public interface BillDiscountMapper {
    BillDiscount getBillDiscount(@Param("billdiscountid") String billdiscountid);

    void createBillDiscount(BillDiscount billDiscount);
}
