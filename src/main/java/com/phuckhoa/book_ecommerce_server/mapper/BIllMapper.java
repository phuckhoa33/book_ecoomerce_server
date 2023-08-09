package com.phuckhoa.book_ecommerce_server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.phuckhoa.book_ecommerce_server.model.Bill;

@Mapper
public interface BIllMapper {
    List<Bill> getAllBills(@Param("userid") String userid);

    Bill getBill(@Param("billid") String billid);

    void paidBill(Bill bill);

}
