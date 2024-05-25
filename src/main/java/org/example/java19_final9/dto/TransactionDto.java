package org.example.java19_final9.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class TransactionDto {
    private String senderAccount;
    private String receiverAccount;
    private int amount;
    private Timestamp actDate;
}
