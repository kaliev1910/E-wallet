package org.example.java19_final9.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.java19_final9.dto.TransactionDto;
import org.example.java19_final9.model.Transaction;
import org.example.java19_final9.model.User;
import org.example.java19_final9.repository.ProviderUserRepository;
import org.example.java19_final9.repository.TransactionRepository;
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
    private final ProviderUserRepository providerUserRepository;
    private final UserService userService;

    public boolean savePayment(TransactionDto transactionDto) {
        if (transactionDto.getTransactionType() == 1) {
            Transaction transaction = Transaction.builder()
                    .sender(userRepository.findUserByAccount(transactionDto.getSenderAccount().toString()).get())
                    .destinationAccount(Integer.parseInt(userRepository.findUserByAccount(transactionDto.getReceiverAccount().toString()).get().getAccount()))
                    .amount(transactionDto.getAmount())
                    .transactionType(transactionDto.getTransactionType())
                    .actDate(new Timestamp(System.currentTimeMillis()))
                    .build();
            transactionRepository.save(transaction);
            userService.subMoney(transactionDto.getAmount(), transactionDto.getSenderAccount().toString(), transactionDto.getReceiverAccount().toString());
            return true;
        } else if (transactionDto.getTransactionType() == 2) {
            if (providerUserRepository.findByUserPhone(transactionDto.getReceiverAccount()).isPresent()) {
                Transaction transaction = Transaction.builder()
                        .sender(userRepository.findUserByAccount(transactionDto.getSenderAccount().toString()).get())
                        .destinationAccount(providerUserRepository.findByUserPhone(transactionDto.getReceiverAccount()).get().getUserPhone())
                        .amount(transactionDto.getAmount())
                        .transactionType(transactionDto.getTransactionType())
                        .actDate(new Timestamp(System.currentTimeMillis()))
                        .build();
                transactionRepository.save(transaction);
                userService.subMoneyForProvider(transactionDto.getAmount(), transactionDto.getSenderAccount().toString(), transactionDto.getReceiverAccount().toString());

                return true;
            } else return false;

        }
        return false;
    }

    public List<TransactionDto> getUserTransaction(String account) {
        User user = userService.getUserByAccount(account).get();
        List<Transaction> transactions = transactionRepository.findTransactionsBySenderIdOrDestinationAccount(user.getId(),Integer.parseInt( user.getAccount()));
        return transactions.stream()
                .map(e -> TransactionDto.builder()
                        .senderAccount(Integer.valueOf(e.getSender().getAccount()))
                        .receiverAccount(e.getDestinationAccount())
                        .amount(e.getAmount())
                        .transactionType(e.getTransactionType())
                        .actDate(e.getActDate())
                        .build()
                ).collect(Collectors.toList());

    }
}
