package com.phuckhoa.book_ecommerce_server.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentInputParamDTO {
    private String userid;
    private String billid;
}
