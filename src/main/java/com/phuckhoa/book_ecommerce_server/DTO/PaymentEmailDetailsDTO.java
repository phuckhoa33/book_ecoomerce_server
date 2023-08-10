package com.phuckhoa.book_ecommerce_server.DTO;

import java.util.List;

import com.phuckhoa.book_ecommerce_server.model.Bill;
import com.phuckhoa.book_ecommerce_server.model.Book;
import com.phuckhoa.book_ecommerce_server.model.Discount;
import com.phuckhoa.book_ecommerce_server.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PaymentEmailDetailsDTO {
    private Bill bill;
    private User user;
    private List<Book> bookItems;
    private EmailDetailsDTO emailDetails;
    private List<Discount> discountsService;
}
