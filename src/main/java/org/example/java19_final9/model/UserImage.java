package org.example.java19_final9.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "user_images")
public class UserImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int imageId;
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User userId;
    @Column(name = "file_name")
    private String fileName;

}
