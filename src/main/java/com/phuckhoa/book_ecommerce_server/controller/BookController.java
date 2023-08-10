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

import com.phuckhoa.book_ecommerce_server.DTO.BookInputData;
import com.phuckhoa.book_ecommerce_server.DTO.BookResultDTO;
import com.phuckhoa.book_ecommerce_server.model.Book;
import com.phuckhoa.book_ecommerce_server.service.BookService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping()
    ResponseEntity<?> createBook(@RequestBody BookInputData request) {
        HashMap<String, Object> result = new HashMap<>();
        try {

            BookResultDTO data = bookService.createNewBook(request.getBook(), request.getBookCategories());
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

    @GetMapping("{bookid}")
    ResponseEntity<?> getBook(@PathVariable String bookid) {
        HashMap<String, Object> result = new HashMap<>();
        try {

            Book data = bookService.getBook(bookid);
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

    @GetMapping()
    ResponseEntity<?> getBooks() {
        HashMap<String, Object> result = new HashMap<>();
        try {

            List<Book> data = bookService.getBooks();
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

    @GetMapping("category/{categoryid}")
    ResponseEntity<?> getBooksDependOnCategoryid(@PathVariable String categoryid) {
        HashMap<String, Object> result = new HashMap<>();
        try {

            List<Book> data = bookService.getBooksDependOnCategory(categoryid);
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

    @GetMapping("author/{author}")
    ResponseEntity<?> getBooksDependOnAuthor(@PathVariable String author) {
        HashMap<String, Object> result = new HashMap<>();
        try {

            List<Book> data = bookService.getBooksDependOnAuthor(author);
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
