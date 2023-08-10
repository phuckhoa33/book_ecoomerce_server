package com.phuckhoa.book_ecommerce_server.service;

import com.phuckhoa.book_ecommerce_server.DTO.PaymentEmailDetailsDTO;

public interface EmailService {
    // Method
    // To send a simple email
    String sendSimpleMail(PaymentEmailDetailsDTO details, String template);

    // Method
    // To send an email with attachment
    String sendMailWithAttachment(PaymentEmailDetailsDTO details, String template);
}
