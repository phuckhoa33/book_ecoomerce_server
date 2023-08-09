package com.phuckhoa.book_ecommerce_server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookCategory {
    private long id;
    private long bookid;
    private long categoryid;
}
