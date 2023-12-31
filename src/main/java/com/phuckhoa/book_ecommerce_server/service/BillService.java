package com.phuckhoa.book_ecommerce_server.service;

import java.util.List;

import com.phuckhoa.book_ecommerce_server.DTO.BillInputDataDTO;
import com.phuckhoa.book_ecommerce_server.model.Bill;

public interface BillService {
    List<Bill> getAllBills(String userid);

    Bill getBill(String billid, String userid);

    void paidBill(Bill bill);

    String createNewBill(BillInputDataDTO request);
}
