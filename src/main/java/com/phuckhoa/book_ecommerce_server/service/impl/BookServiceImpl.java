package com.phuckhoa.book_ecommerce_server.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phuckhoa.book_ecommerce_server.DTO.BookResultDTO;
import com.phuckhoa.book_ecommerce_server.mapper.BookCategoryMapper;
import com.phuckhoa.book_ecommerce_server.mapper.BookMapper;
import com.phuckhoa.book_ecommerce_server.mapper.CategoryMapper;
import com.phuckhoa.book_ecommerce_server.model.Book;
import com.phuckhoa.book_ecommerce_server.model.BookCategory;
import com.phuckhoa.book_ecommerce_server.model.Category;
import com.phuckhoa.book_ecommerce_server.service.BookService;
import com.phuckhoa.book_ecommerce_server.service.ExtraService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    BookCategoryMapper bookCategoryMapper;

    @Autowired
    ExtraService extraService;

    @Override
    public void createBook(Book book) {
        bookMapper.createBook(book);
    }

    @Override
    public Book getBook(String bookid) {
        return bookMapper.getBook(bookid);
    }

    @Override
    public List<Book> getBooks() {
        return bookMapper.getBooks();
    }

    public BookResultDTO createNewBook(Book book, List<Long> bookCategories) {
        Book oldBook = getBook(String.valueOf(book.getTitle()));
        long bookid = this.extraService.createRandomId(10);

        // Check book is exist
        if (oldBook != null && (oldBook.getTitle().equals(book.getTitle()) || oldBook.getId() == book.getId())) {
            return new BookResultDTO(oldBook, "This book is exist");
        }

        // Check category
        List<Category> categories = categoryMapper.getCategories();

        Set<Long> categoryIds = new HashSet<>();
        for (Category category : categories) {
            categoryIds.add(category.getId());
        }

        if (extraService.checkListExistInASet(categoryIds, bookCategories) == false) {
            return new BookResultDTO(book, "Category is invalid");
        }

        // Create book

        Thread createBookThread = new Thread(() -> {
            book.setId(bookid);
            this.createBook(book);
        });
        Thread createBookCategoryThread = new Thread(() -> {
            for (Long categoryid : bookCategories) {
                BookCategory newBookCategory = new BookCategory(extraService.createRandomId(10), bookid, categoryid);
                bookCategoryMapper.createBookCategory(newBookCategory);
            }
        });

        createBookThread.start();
        createBookCategoryThread.start();
        return new BookResultDTO(book, "Create book is successfully");
    }

    @Override
    public List<Book> getBooksDependOnCategory(String bookCategory) {
        return bookMapper.getBooksDependOnCategory(bookCategory);
    }

    @Override
    public List<Book> getBooksDependOnAuthor(String author) {
        return bookMapper.getBooksDependOnAuthor(author);
    }
}
