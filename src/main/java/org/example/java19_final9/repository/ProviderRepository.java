package org.example.java19_final9.repository;

import org.example.java19_final9.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider,Integer> {
    Optional<Provider> findById(int id);
}
