package com.phuckhoa.book_ecommerce_server.service.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phuckhoa.book_ecommerce_server.DTO.BillInputDataDTO;
import com.phuckhoa.book_ecommerce_server.DTO.EmailDetailsDTO;
import com.phuckhoa.book_ecommerce_server.DTO.PaymentDetailsDTO;
import com.phuckhoa.book_ecommerce_server.DTO.PaymentEmailDetailsDTO;
import com.phuckhoa.book_ecommerce_server.mapper.BillDiscountMapper;
import com.phuckhoa.book_ecommerce_server.mapper.BillItemMapper;
import com.phuckhoa.book_ecommerce_server.mapper.BillMapper;
import com.phuckhoa.book_ecommerce_server.mapper.BookMapper;
import com.phuckhoa.book_ecommerce_server.mapper.DiscountMapper;
import com.phuckhoa.book_ecommerce_server.mapper.UserMapper;
import com.phuckhoa.book_ecommerce_server.model.Bill;
import com.phuckhoa.book_ecommerce_server.model.BillDiscount;
import com.phuckhoa.book_ecommerce_server.model.BillItem;
import com.phuckhoa.book_ecommerce_server.model.Book;
import com.phuckhoa.book_ecommerce_server.model.Discount;
import com.phuckhoa.book_ecommerce_server.service.BillService;
import com.phuckhoa.book_ecommerce_server.service.EmailService;
import com.phuckhoa.book_ecommerce_server.service.ExtraService;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    BillMapper billMapper;

    @Autowired
    BillItemMapper billItemMapper;

    @Autowired
    DiscountMapper discountMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    BookMapper bookMapper;

    @Autowired
    BillDiscountMapper billDiscountMapper;

    @Autowired
    ExtraService extraService;

    @Autowired
    EmailService emailService;

    @Override
    public List<Bill> getAllBills(String userid) {
        return billMapper.getAllBills(userid);
    }

    @Override
    public Bill getBill(String billid, String userid) {
        return billMapper.getBill(billid, userid);
    }

    @Override
    public void paidBill(Bill bill) {
        billMapper.paidBill(bill);
    }

    @Override
    public String createNewBill(BillInputDataDTO request) {
        Bill newBill = request.getBill();
        List<String> discountList = request.getDiscountList();
        List<BillItem> billItems = request.getBillItems();
        final String[] message = { "Order bill is successfully" };

        Long billid = extraService.createRandomId(10);

        // Create Thread
        // Create bill
        CompletableFuture<Void> createNewBillFuture = CompletableFuture.runAsync(() -> {
            newBill.setId(billid);
            paidBill(newBill);

        });
        // create billItems
        CompletableFuture<Void> createNewBillItemFuture = CompletableFuture.runAsync(() -> {
            for (BillItem billItem : billItems) {
                Book book = bookMapper.getBookDependById(billItem.getBookid());
                if (book == null) {
                    message[0] = "Book is not exist";
                    createNewBillFuture.cancel(true);
                }
                billItem.setId(extraService.createRandomId(10));
                billItem.setBillid(billid);
                billItemMapper.createBillItem(billItem);
            }

        });

        // create billDiscount

        CompletableFuture<Void> createNewBillDiscountFuture = CompletableFuture.runAsync(() -> {
            for (String discount : discountList) {
                Discount discountItem = discountMapper.getDiscountByDiscountid(discount);
                if (discountItem == null) {
                    message[0] = "Discount label is invalid";
                    createNewBillFuture.cancel(true);
                    createNewBillItemFuture.cancel(true);
                    return;
                }
                BillDiscount newBillDiscount = new BillDiscount();
                newBillDiscount.setBillid(billid);
                newBillDiscount.setDiscountid(discount);
                newBillDiscount.setId(extraService.createRandomId(10));
                billDiscountMapper.createBillDiscount(newBillDiscount);

            }

        });

        // Send Email
        CompletableFuture<Void> sendEmail = CompletableFuture.runAsync(() -> {
            try {

                PaymentEmailDetailsDTO paymentEmail = new PaymentEmailDetailsDTO();
                PaymentDetailsDTO emailDetails = new PaymentDetailsDTO();
                emailDetails.setRecipient("mphuc8671@gmail.com");
                emailDetails.setSubject(message[0]);
                emailDetails.setMsgBody(message[0]);
                paymentEmail.setEmailDetails(emailDetails);
                paymentEmail.setBill(newBill);
                emailService.sendSimpleMailWithPayment(paymentEmail, "payment");
            } catch (Exception e) {
                // TODO: handle exception
                message[0] = "Failed to send email";
                createNewBillFuture.cancel(true);
                createNewBillDiscountFuture.cancel(true);
                createNewBillItemFuture.cancel(true);
            }
        });
        // Start Thread
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(
                createNewBillFuture,
                createNewBillItemFuture,
                createNewBillDiscountFuture, sendEmail);

        // Wait all thread is ended

        try {
            combinedFuture.get(); // Wait for all tasks to complete
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            // Handle exceptions here
        }

        return message[0];
    }

}
