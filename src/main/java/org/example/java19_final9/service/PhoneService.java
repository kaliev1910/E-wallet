package org.example.java19_final9.service;

import org.example.java19_final9.dto.PhoneDto;
import org.example.java19_final9.model.Phone;
import org.example.java19_final9.repository.PhoneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PhoneService {
    private final PhoneRepository phoneRepository;
    private final UserService userService;

    public int save(PhoneDto phoneDto){
        Phone phone=Phone.builder()
                .user(userService.getUserById(phoneDto.getUserId()).get())
                .phone(phoneDto.getPhone())
                .build();
        return phoneRepository.save(phone).getId();
    }
}
