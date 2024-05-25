package org.example.java19_final9.service;

import org.example.java19_final9.dto.TransactionDto;
import org.example.java19_final9.model.Transaction;
import org.example.java19_final9.model.User;
import org.example.java19_final9.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserService userService;

    public void saveUserPayment(TransactionDto transactionDto) {
        Transaction transaction = Transaction.builder()
                .sender(userService.getUserByAccount(transactionDto.getSenderAccount()).get())
                .destination(transactionDto.getReceiverAccount())
                .amount(transactionDto.getAmount())
                .actDate(new Timestamp(System.currentTimeMillis()))
                .build();
        transactionRepository.save(transaction);
        userService.subMoney(transactionDto.getAmount(), transactionDto.getSenderAccount(), transactionDto.getReceiverAccount());
    }

    public List<TransactionDto> getUserTransaction(String account) {
        User user = userService.getUserByAccount(account).get();
        List<Transaction> transactions = transactionRepository.findTransactionsBySenderIdOrReceiverId(user.getId(), user.getId());
        List<TransactionDto> transactionDtos = transactions.stream()
                .map(e -> TransactionDto.builder()
                        .senderAccount(e.getSender().getAccount())
                        .receiverAccount(e.getDestination())
                        .amount(e.getAmount())
                        .actDate(e.getActDate())
                        .build()
                ).collect(Collectors.toList());
        return transactionDtos;

    }
}
