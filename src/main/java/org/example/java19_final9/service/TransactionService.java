package org.example.java19_final9.service;

import org.example.java19_final9.dto.TransactionToUserDto;
import org.example.java19_final9.model.Transaction;
import org.example.java19_final9.model.User;
import org.example.java19_final9.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.java19_final9.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public void saveUserPayment(TransactionToUserDto transactionToUserDto) {
        if (transactionToUserDto.getTransactionType()==1){
            Transaction transaction = Transaction.builder()
                    .sender(userRepository.getUserById(transactionToUserDto.getSenderAccount()))

                    .destinationAccount(Integer.parseInt( userRepository.getUserById(transactionToUserDto.getSenderAccount()).getAccount()))
                    .amount(transactionToUserDto.getAmount())
                    .actDate(new Timestamp(System.currentTimeMillis()))
                    .build();
            transactionRepository.save(transaction);
            userService.subMoney(transactionToUserDto.getAmount(), transactionToUserDto.getSenderAccount().toString(), transactionToUserDto.getReceiverAccount().toString());
        }

    }

    public List<TransactionToUserDto> getUserTransaction(String account) {
        User user = userService.getUserByAccount(account).get();
        List<Transaction> transactions = transactionRepository.findTransactionsBySenderId(user.getId());
        return transactions.stream()
                .map(e -> TransactionToUserDto.builder()
                        .senderAccount(Integer.valueOf(e.getSender().getAccount()))
                        .receiverAccount(e.getDestinationAccount())
                        .amount(e.getAmount())
                        .actDate(e.getActDate())
                        .build()
                ).collect(Collectors.toList());

    }
}
