package org.example.java19_final9.repository;

import org.example.java19_final9.model.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserImageRepository extends JpaRepository<UserImage, Integer> {
    Optional<UserImage> findByUserId_Id(long userId);

    void deleteByUserId_Id(long userId);

    UserImage findByImageId(long imageId);
}
