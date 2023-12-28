package com.phuckhoa.book_ecommerce_server.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phuckhoa.book_ecommerce_server.DTO.BillInputDataDTO;
import com.phuckhoa.book_ecommerce_server.DTO.BillResultDTO;
import com.phuckhoa.book_ecommerce_server.DTO.PaymentInputParamDTO;
import com.phuckhoa.book_ecommerce_server.model.Bill;
import com.phuckhoa.book_ecommerce_server.service.BillService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/bill")
public class BillController {
    @Autowired
    BillService billService;

    @PostMapping()
    ResponseEntity<?> createBill(@RequestBody BillInputDataDTO request) {
        HashMap<String, Object> result = new HashMap<>();
        try {
            String message = billService.createNewBill(request);
            BillResultDTO data = new BillResultDTO(request.getBill(), message);
            result.put("success", true);
            result.put("message", "Success to call API for bill process");
            result.put("data", data);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Fail to call API for bill process");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("{userid}")
    ResponseEntity<?> getBillByUserId(@PathVariable String userid) {
        HashMap<String, Object> result = new HashMap<>();
        try {

            List<Bill> data = billService.getAllBills(userid);
            result.put("success", true);
            result.put("message", "Success to call API for bill process");
            result.put("data", data);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Fail to call API for bill process");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/payment/{userid}/{billid}")
    ResponseEntity<?> getPayment(@PathVariable PaymentInputParamDTO request) {
        HashMap<String, Object> result = new HashMap<>();
        try {

            Bill data = billService.getBill(request.getBillid(), request.getUserid());
            result.put("success", true);
            result.put("message", "Success to call API for bill process");
            result.put("data", data);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Fail to call API for bill process");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }
}
