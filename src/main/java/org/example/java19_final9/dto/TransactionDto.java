package org.example.java19_final9.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class TransactionDto {
    private Integer senderAccount;
    private int transactionType;
    private Integer receiverAccount;

    private int amount;
    private Timestamp actDate;
}
