package org.example.java19_final9.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class TransactionToProviderDto {
    private String senderAccount;
    private int destinationAccount;
    private int amount;
    private Timestamp actDate;
}
