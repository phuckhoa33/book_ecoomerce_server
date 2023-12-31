package com.phuckhoa.book_ecommerce_server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BillDiscount {
    private long id;
    private String discountid;
    private long billid;
}
