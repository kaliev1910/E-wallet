package org.example.java19_final9.repository;

import jakarta.transaction.Transactional;
import org.example.java19_final9.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO USER_ROLES(USER_ID, ROLE_ID) VALUES (:id, :roleId)", nativeQuery = true)
    void addUserRole(@Param("roleId") int roleId, @Param("id") int id);
}
