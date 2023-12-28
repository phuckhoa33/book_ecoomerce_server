package com.phuckhoa.book_ecommerce_server.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phuckhoa.book_ecommerce_server.DTO.DiscountResultDTO;
import com.phuckhoa.book_ecommerce_server.model.Discount;
import com.phuckhoa.book_ecommerce_server.service.DiscountService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/discount")
public class DiscountController {

    @Autowired
    DiscountService discountService;

    @PostMapping()
    ResponseEntity<?> createDiscount(@RequestBody Discount discount) {
        HashMap<String, Object> result = new HashMap<>();
        try {

            String message = discountService.createNewDiscount(discount);
            System.out.println(message);
            DiscountResultDTO data = new DiscountResultDTO(discount, message);
            result.put("success", true);
            result.put("message", "Success to call API discount book");
            result.put("data", data);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Fail to call API discount book");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping()
    ResponseEntity<?> getDiscounts() {
        HashMap<String, Object> result = new HashMap<>();
        try {

            List<Discount> data = discountService.getAllDiscounts();
            result.put("success", true);
            result.put("message", "Success to call API create book");
            result.put("data", data);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Fail to call API create book");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }
}
