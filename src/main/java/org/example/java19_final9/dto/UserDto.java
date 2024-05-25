package org.example.java19_final9.dto;

import org.example.java19_final9.enums.AccountType;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private  int id;

    @Email
    @NotEmpty
    private String email;
    private AccountType accountType;
    private int balance;

    @NotBlank
    @Size(min = 4, max = 24, message = "Length must be >= 4 and <= 24")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$", message = "Should contain at least one uppercase letter, one number")
    private String password;
    private String resetPasswordToken;
    private String account;


}
