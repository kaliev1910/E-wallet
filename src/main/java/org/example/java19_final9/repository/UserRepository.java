package org.example.java19_final9.repository;

import jakarta.transaction.Transactional;
import org.example.java19_final9.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select m from User m where m.id = :id")
    User getUserById(Integer id);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByAccount(String account);

    Optional<User> findByResetPasswordToken(String token);

    Boolean existsByAccount(String account);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.balance = :newBalance WHERE u.account = :accountValue")
    int updateBalanceByAccount(@Param("accountValue") String accountValue, @Param("newBalance") int newBalance);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.balance = :newBalance WHERE u.id = :accountValue")
    int updateBalanceById(@Param("accountValue") int accountValue, @Param("newBalance") int newBalance);
}
