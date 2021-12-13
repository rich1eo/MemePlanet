package com.example.memeplanet.repositoty;

import com.example.memeplanet.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
