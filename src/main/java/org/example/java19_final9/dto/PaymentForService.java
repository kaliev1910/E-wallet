package org.example.java19_final9.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentForService {
    private int providerId;
    private int balance;
    private int phone;
}
