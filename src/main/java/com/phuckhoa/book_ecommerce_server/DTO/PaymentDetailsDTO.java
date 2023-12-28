package com.phuckhoa.book_ecommerce_server.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentDetailsDTO {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
