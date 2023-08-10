package com.phuckhoa.book_ecommerce_server.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailDetailsDTO {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
