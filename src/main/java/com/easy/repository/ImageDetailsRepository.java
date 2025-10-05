package com.easy.repository;

import com.easy.entity.ImageDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDetailsRepository  extends JpaRepository<ImageDetailsEntity,Long> {
}
