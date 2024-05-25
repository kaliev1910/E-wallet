package org.example.java19_final9.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.java19_final9.dto.UserImageDto;
import org.example.java19_final9.model.UserImage;
import org.example.java19_final9.repository.UserImageRepository;
import org.example.java19_final9.repository.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserImageService {
    private static final String SUB_DIR = "images";
    private final FileService fileService;

    private final UserImageRepository userImageRepository;
    private final UserRepository userRepository;

    public void uploadImage(UserImageDto userImageDto) {
        try {

            UserImage found = userImageRepository.findByUserId_Id(userImageDto.getUserId()).orElseThrow();

            userImageRepository.delete(found);
            String fileName = fileService.saveUploadedFile(userImageDto.getFile(), SUB_DIR);
            UserImage ui = UserImage.builder()
                    .userId(
                            userRepository.getUserById
                                    (
                                            (int) userImageDto.getUserId()
                                    )
                    )
                    .fileName(fileName)
                    .build();
            log.error(String.valueOf(ui.getUserId()), ui.getImageId());
            userImageRepository.save(ui);
            log.info("Image uploaded successfully");
        } catch (Exception e) {
            log.error(e.getMessage(), "Image upload failed");

        }
    }


    public ResponseEntity<?> downloadImage(long imageId) {
        String fileName;
        try {
            UserImage ui = userImageRepository.findByImageId(imageId);
            fileName = ui.getFileName();
        } catch (NullPointerException e) {
            throw new NoSuchElementException("Image not found");
        }
        return fileService.getOutputFile(fileName, SUB_DIR, MediaType.IMAGE_JPEG);
    }

    public ResponseEntity<?> getImageByUserId(Long userId) {
        var optionalUserImage = userImageRepository.findByUserId_Id(userId);
        if (optionalUserImage.isEmpty()) {
            return fileService.getOutputFile("no_image.jpeg", SUB_DIR, MediaType.IMAGE_JPEG);
        }
        return fileService.getOutputFile(optionalUserImage.get().getFileName(), SUB_DIR, MediaType.IMAGE_JPEG);
    }
}
