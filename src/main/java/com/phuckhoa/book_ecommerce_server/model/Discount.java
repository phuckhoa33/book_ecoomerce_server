package com.phuckhoa.book_ecommerce_server.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Discount {
    private long id;
    private Date start_date;
    private Date end_date;
    private Double percent;
    private String title;
}
