package org.example.java19_final9.repository;

import org.example.java19_final9.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone,Integer> {
}
