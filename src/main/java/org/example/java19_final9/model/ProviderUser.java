package org.example.java19_final9.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "provider_users")
public class ProviderUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Provider provider;
    private int balance;
    @Column(name = "user_phone")
    private int userPhone;

}
