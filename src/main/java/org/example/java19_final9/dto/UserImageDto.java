package org.example.java19_final9.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserImageDto {
    private MultipartFile file;
    private long userId;
}
