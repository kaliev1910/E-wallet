package org.example.java19_final9.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "provider_users")
public class ProviderUsers {
    @Id
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Provider provider;
    private int balance;
    private int userAccount;

}
