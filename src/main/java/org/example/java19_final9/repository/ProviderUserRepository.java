package org.example.java19_final9.repository;

import org.example.java19_final9.model.ProviderUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderUserRepository extends JpaRepository<ProviderUser, Integer> {

    Optional<ProviderUser> findByUserPhone(int phone);
}
