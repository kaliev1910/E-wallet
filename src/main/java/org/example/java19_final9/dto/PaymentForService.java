package org.example.java19_final9.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentForService {
    private int id;
    private int providerId;
    private int amount;
    private int userPhone;
}
