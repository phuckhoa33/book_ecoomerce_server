package com.phuckhoa.book_ecommerce_server.DTO;

import java.util.List;

import com.phuckhoa.book_ecommerce_server.model.Bill;
import com.phuckhoa.book_ecommerce_server.model.BillItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BillInputDataDTO {
    private Bill bill;
    private List<String> discountList;
    private List<BillItem> billItems;
}
