package org.example.java19_final9.service;

import org.example.java19_final9.dto.ProviderDto;
import org.example.java19_final9.model.Provider;
import org.example.java19_final9.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProviderService {
    private final ServiceRepository serviceRepository;

    public List<ProviderDto> getAllServices() {
        List<Provider> services = serviceRepository.findAll();
        List<ProviderDto> providerDtos = services.stream()
                .map(e -> ProviderDto.builder()
                        .id(e.getId())
                        .provider(e.getProvider())
                        .build()
                ).collect(Collectors.toList());
        return providerDtos;
    }

    public ProviderDto getServiceById(int serviceId) {
        Provider service = serviceRepository.findById(serviceId).orElse(null);
        if (service == null) {
            return null;
        }
        return ProviderDto.builder()
                .id(service.getId())
                .provider(service.getProvider())
                .build();
    }
}
