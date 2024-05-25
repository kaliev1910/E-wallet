package org.example.java19_final9.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProviderDto {
    private int id;
    private String provider;
}
