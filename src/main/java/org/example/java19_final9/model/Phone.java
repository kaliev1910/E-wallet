package org.example.java19_final9.model;

import jakarta.persistence.*;
import lombok.*;



@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "phones")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)

    @JoinColumn(name = "user_id")
    private User user;

    private String phone;

}
