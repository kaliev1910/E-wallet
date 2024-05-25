package org.example.java19_final9.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.java19_final9.dto.PaymentForService;
import org.example.java19_final9.dto.ProviderDto;
import org.example.java19_final9.dto.ProviderUserDto;
import org.example.java19_final9.model.Provider;
import org.example.java19_final9.model.ProviderUser;
import org.example.java19_final9.repository.ProviderRepository;
import org.example.java19_final9.repository.ProviderUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProviderService {
    private final ProviderRepository providerRepository;

    private final ProviderUserRepository providerUserRepository;

    public List<ProviderDto> getAllServices() {
        List<Provider> services = providerRepository.findAll();
        return services.stream()
                .map(e -> ProviderDto.builder()
                        .id(e.getId())
                        .provider(e.getProvider())
                        .build()
                ).collect(Collectors.toList());
    }

    public ProviderDto getProviderById(int serviceId) {
        Provider service = providerRepository.findById(serviceId).orElse(null);
        if (service == null) {
            return null;
        }
        return ProviderDto.builder()
                .id(service.getId())
                .provider(service.getProvider())
                .build();
    }

    public ProviderUserDto addProviderUser(ProviderUserDto providerUserDto) {
        ProviderUser providerUser = ProviderUser.builder()
                .userPhone(providerUserDto.getUserPhone())
                .provider(providerRepository.findById(providerUserDto.getProviderId()).get())
                .build();


        return toDto(providerUserRepository.saveAndFlush(providerUser));
    }

    public String replenishProviderUser(PaymentForService payment) {
        ProviderUser providerUser = new ProviderUser();
        if (providerUserRepository.findByUserPhone(payment.getUserPhone()).isPresent()) {
            providerUser = providerUserRepository.findByUserPhone(payment.getUserPhone()).get();
            providerUser.setBalance(providerUser.getBalance() + payment.getAmount());
            providerUserRepository.save(providerUser);
            return "success";
        } else {
            return "fail";
        }


    }

    public ProviderUserDto getProviderUserById(int providerUserId) {
        return toDto(providerUserRepository.findById(providerUserId).orElseThrow(null));
    }

    private ProviderUserDto toDto(ProviderUser providerUser) {
        return ProviderUserDto.builder()
                .id(providerUser.getId())
                .userPhone(providerUser.getUserPhone())
                .balance(providerUser.getBalance())
                .providerId(providerUser.getProvider().getId())
                .build();
    }

}

