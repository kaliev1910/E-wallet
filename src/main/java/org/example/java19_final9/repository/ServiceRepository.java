package org.example.java19_final9.repository;

import org.example.java19_final9.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Provider,Integer> {
}
