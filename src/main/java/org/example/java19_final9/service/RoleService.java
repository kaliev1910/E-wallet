package org.example.java19_final9.service;

import org.example.java19_final9.model.Role;
import org.example.java19_final9.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j

public class RoleService {
    private final RoleRepository roleRepository;

    public Role getRoleById(long id){
        return roleRepository.findById((int) id).get();
    }

}
