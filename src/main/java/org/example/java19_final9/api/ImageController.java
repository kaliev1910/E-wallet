package org.example.java19_final9.api;

import lombok.RequiredArgsConstructor;
import org.example.java19_final9.service.UserImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {
    private final UserImageService userImageService;

    @GetMapping("/download/{imageId}")
    public ResponseEntity<?> downloadImage(@PathVariable long imageId) {
        return userImageService.downloadImage(imageId);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<?> getImageByUserId(@PathVariable Long userId) {
        return userImageService.getImageByUserId(userId);
    }
}
