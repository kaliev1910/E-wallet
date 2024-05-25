package org.example.java19_final9.repository;

import org.example.java19_final9.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    List<Transaction> findTransactionsBySenderId(int senderId);

    List<Transaction> findTransactionsBySenderIdOrDestinationAccount(int senderId,int receiverId);
}
