package com.phuckhoa.book_ecommerce_server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.phuckhoa.book_ecommerce_server.model.BillItem;

@Mapper
public interface BillItemMapper {
    List<BillItem> getBillItems(@Param("billitemid") String billitemid);

    void createBillItem(BillItem billItem);
}
